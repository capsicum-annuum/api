package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.NeedStatus;
import br.com.annuum.capsicum.api.mapper.AttributeMachCodeMapper;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.AbstractDocument;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovementListenerTest {

    @InjectMocks
    private MovementListener target;

    @Mock
    private EntityPersister persister;

    @Mock
    private EntityMetamodel metamodel;

    @Mock
    private AttributeMachCodeMapper attributeMachCodeMapper;

    @Test
    public void shouldEncodeTheEncodableAttributesOnThatWasSettedDuringMovementPersistence() {
        // Arrange
        final Skill skill = new Skill()
            .setId(1L)
            .setName("skill")
            .setBinaryIdentifier(2);
        final String skillBinaryIdentifier = "10";
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("cause")
            .setBinaryIdentifier(2);
        final String expectedCauseBinaryCode = "10";
        final UserVolunteer userVolunteer = new UserVolunteer()
            .setUserSkills(Collections.singletonList(skill))
            .setCauseThatSupport(Collections.singletonList(cause));
        final Need need = new Need()
            .setNeedStatus(NeedStatus.ACTIVE)
            .setId(1L)
            .setSkill(skill)
            .setSkillMatchCode(skillBinaryIdentifier);
        final Movement entity = new Movement()
            .setAddress(Mockito.mock(Address.class))
            .setCauseThatSupport(Collections.singletonList(cause))
            .setNeeds(Collections.singletonList(need))
            .setUserAuthor(userVolunteer);
        final Long id = 0L;
        final String[] propertyNames = new String[]{"causeMatchCode"};
        final Object[] state = new String[]{""};
        final PreInsertEvent event = new PreInsertEvent(entity, id, state, persister, null);

        when(attributeMachCodeMapper.mapFromList(entity.getCauseThatSupport()))
            .thenReturn(expectedCauseBinaryCode);
        when(persister.getEntityMetamodel())
            .thenReturn(metamodel);
        when(metamodel.getPropertyNames())
            .thenReturn(propertyNames);

        // Act
        final boolean result = target.onPreInsert(event);

        // Assert
        assertFalse(result);
        assertEquals(expectedCauseBinaryCode, state[0]);
    }
}
