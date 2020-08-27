package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.mapper.AttributeMachCodeMapper;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserVolunteerListenerTest {

    @InjectMocks
    private UserVolunteerListener target;

    @Mock
    private EntityPersister persister;

    @Mock
    private EntityMetamodel metamodel;

    @Mock
    private AttributeMachCodeMapper attributeMachCodeMapper;

    @Test
    public void shouldEncodeTheEncodableAttributesOnThatWasSettedDuringUserVolunteerPersistence() {
        // Arrange
        final Skill skill = new Skill()
            .setId(1L)
            .setName("skill")
            .setBinaryIdentifier(2);
        final Integer expectedSkillBinaryCode = 2;
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("cause")
            .setBinaryIdentifier(2);
        final Integer expectedCauseBinaryCode = 2;
        final UserVolunteer entity = new UserVolunteer()
            .setUserSkills(Collections.singletonList(skill))
            .setCauseThatSupport(Collections.singletonList(cause));
        final Long id = 0L;
        final String[] propertyNames = new String[]{"skillMatchCode", "causeMatchCode"};
        final Object[] state = new Integer[]{0, 0};
        final PreInsertEvent event = new PreInsertEvent(entity, id, state, persister, null);

        when(attributeMachCodeMapper.map(entity.getUserSkills()))
            .thenReturn(expectedSkillBinaryCode);
        when(attributeMachCodeMapper.map(entity.getCauseThatSupport()))
            .thenReturn(expectedCauseBinaryCode);
        when(persister.getEntityMetamodel())
            .thenReturn(metamodel);
        when(metamodel.getPropertyNames())
            .thenReturn(propertyNames);

        // Act
        final boolean result = target.onPreInsert(event);

        // Assert
        assertFalse(result);
        assertEquals(expectedSkillBinaryCode, state[0]);
        assertEquals(expectedCauseBinaryCode, state[1]);
    }
}
