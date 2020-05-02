package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
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
import java.util.List;

@Slf4j
@Component
public class AttributeEncodeListener implements PreInsertEventListener, PreUpdateEventListener {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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
        log.info("AttributeEncodeListener :: onSave");

        if (entity instanceof UserVolunteer) {
            log.info("AttributeEncodeListener :: onSave :: HasEncodedSkills");

            final List<Skill> skills = ((UserVolunteer) entity).getUserSkills();
            final List<Cause> causes = ((UserVolunteer) entity).getCauseThatSupport();

            StringBuilder builder = new StringBuilder();
            skills.forEach(encodable -> {
                builder.append(Integer.toBinaryString(encodable.getBinaryIdentifier()));
            });
            final String skillMatchCode = builder.toString();

            // Clean the buffer to use again with causes
            builder.setLength(0);

            causes.forEach(encodable -> {
                builder.append(Integer.toBinaryString(encodable.getBinaryIdentifier()));
            });
            final String causeMatchCode = builder.toString();

            setValue(persister, currentState, entity, new String[]{"skillMatchCode", "causeMatchCode"}, new Object[]{skillMatchCode, causeMatchCode});
        }

        return false;
    }

    private void setValue(EntityPersister persister, Object[] currentState, Object entity, String[] propertiesToSet, Object[] valuesToSet) {
        final String[] propertyNames = persister.getEntityMetamodel().getPropertyNames();

        assert propertiesToSet.length == valuesToSet.length;
        for (int i = 0; i < propertiesToSet.length; i++) {
            int index = ArrayUtils.indexOf(propertyNames, propertiesToSet[i]);
            if (index >= 0) {
                log.info("AttributeEncodeListener :: onSave :: HasEncodedSkills :: setNewValues");
                currentState[index] = valuesToSet[i];
            } else {
                log.error("Field '{}' not found on entity '{}'.", propertiesToSet[i], entity.getClass().getName());
            }
        }
    }
}
