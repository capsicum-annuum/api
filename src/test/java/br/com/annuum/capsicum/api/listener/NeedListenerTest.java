package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.enums.NeedStatus;
import br.com.annuum.capsicum.api.mapper.AttributeMachCodeMapper;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NeedListenerTest {

    @InjectMocks
    private NeedListener target;

    @Mock
    private EntityPersister persister;

    @Mock
    private EntityMetamodel metamodel;

    @Mock
    private AttributeMachCodeMapper attributeMachCodeMapper;

    @Test
    public void shouldEncodeTheEncodableAttributesOnThatWasSettedDuringNeedPersistence() {
        // Arrange
        final Skill skill = new Skill()
            .setId(1L)
            .setName("skill")
            .setBinaryIdentifier(2);
        final String expectedSkillBinaryCode = "10";
        final Need entity = new Need()
            .setNeedStatus(NeedStatus.ACTIVE)
            .setId(1L)
            .setSkill(skill)
            .setSkillMatchCode(expectedSkillBinaryCode);
        final Long id = 0L;
        final String[] propertyNames = new String[]{"skillMatchCode"};
        final Object[] state = new String[]{""};
        final PreInsertEvent event = new PreInsertEvent(entity, id, state, persister, null);

        when(attributeMachCodeMapper.map(entity.getSkill().getBinaryIdentifier()))
            .thenReturn(expectedSkillBinaryCode);
        when(persister.getEntityMetamodel())
            .thenReturn(metamodel);
        when(metamodel.getPropertyNames())
            .thenReturn(propertyNames);

        // Act
        final boolean result = target.onPreInsert(event);

        // Assert
        assertFalse(result);
        assertEquals(expectedSkillBinaryCode, state[0]);
    }
}
