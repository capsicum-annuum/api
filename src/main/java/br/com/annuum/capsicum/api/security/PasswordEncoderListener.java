package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.domain.HasEncodedPassword;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PasswordEncoderListener implements PreInsertEventListener, PreUpdateEventListener {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  private void init() {
    SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
    EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
    registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
  }

  @Override
  public boolean onPreInsert(final PreInsertEvent event) {
    return onSave(event.getPersister(), event.getState(), event.getEntity());
  }

  @Override
  public boolean onPreUpdate(PreUpdateEvent event) {
    return onSave(event.getPersister(), event.getState(), event.getEntity());
  }

  private boolean onSave(EntityPersister persister, Object[] currentState, Object entity) {
    log.info("PasswordEncoderListener :: onSave");

    if (entity instanceof HasEncodedPassword) {
      log.info("PasswordEncoderListener :: onSave :: HasEncodedPassword");

      final HasEncodedPassword passwordOwner = (HasEncodedPassword) entity;
      final String encodedPassword = passwordEncoder.encode(passwordOwner.getPassword());

      setValue(persister, currentState, entity, "password", encodedPassword);
    }

    return false;
  }

  private void setValue(EntityPersister persister, Object[] currentState, Object entity, String propertyToSet, Object value) {
    final String[] propertyNames = persister.getEntityMetamodel().getPropertyNames();

    int index = ArrayUtils.indexOf(propertyNames, propertyToSet);
    if (index < 0) {
      log.error("Field '{}' not found on entity '{}'.", propertyToSet, entity.getClass().getName());
      return;
    }

    log.info("PasswordEncoderListener :: onSave :: HasEncodedPassword :: setEncodedPassword");
    currentState[index] = value;
  }

}