package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.mapper.AttributeMachCodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.*;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Slf4j
@Component
public class MovementListener implements PreInsertEventListener, PreUpdateEventListener {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private AttributeMachCodeMapper attributeMachCodeMapper;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(this);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        return onSave(event.getPersister(), event.getState(), event.getEntity());
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        return onSave(event.getPersister(), event.getState(), event.getEntity());
    }

    private boolean onSave(EntityPersister persister, Object[] currentState, Object entity) {
        log.info("MovementListener :: onSave");

        if (entity instanceof Movement) {
            log.info("MovementListener :: onSave :: HasEncodedCauses");
            final Integer causeMatchCode = attributeMachCodeMapper.map(((Movement) entity).getCauseThatSupport());

            setValue(persister, currentState, entity, new String[]{"causeMatchCode"}, new Object[]{causeMatchCode});
        }
        return false;
    }

    private void setValue(EntityPersister persister, Object[] currentState, Object entity, String[] propertiesToSet, Object[] valuesToSet) {
        final String[] propertyNames = persister.getEntityMetamodel().getPropertyNames();

        assert propertiesToSet.length == valuesToSet.length;
        for (int i = 0; i < propertiesToSet.length; i++) {
            int index = ArrayUtils.indexOf(propertyNames, propertiesToSet[i]);
            if (index >= 0) {
                log.info("MovementListener :: onSave :: HasEncodedSkills :: setNewValues");
                currentState[index] = valuesToSet[i];
            } else {
                log.error("Field '{}' not found on entity '{}'.", propertiesToSet[i], entity.getClass().getName());
            }
        }
    }

}
