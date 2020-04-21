package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.domain.SpatialLocation;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpatialLocationListener implements PreLoadEventListener, PreUpdateEventListener, PreInsertEventListener {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private PointFactory pointFactory;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(this);
        registry.getEventListenerGroup(EventType.PRE_LOAD).appendListener(this);
    }

    @Override
    public boolean onPreInsert(final PreInsertEvent event) {
        return onSave(event.getPersister(), event.getState(), event.getEntity());
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        return onSave(event.getPersister(), event.getState(), event.getEntity());
    }

    @Override
    public void onPreLoad(PreLoadEvent event) {
        log.info("SpatialLocationListener :: onLoad");

        Object entity = event.getEntity();

        if (entity instanceof SpatialLocated) {
            log.info("SpatialLocationListener :: onLoad :: SpatialLocated");

            Object[] state = event.getState();
            final SpatialLocation location = new SpatialLocation();
            for (Object field : state) {
                if (field instanceof Point) {
                    final Point point = (Point) field;
                    location
                        .setLatitude(point.getX())
                        .setLongitude(point.getY());
                }
            }

            ((SpatialLocated) entity).setSpatialLocation(location);
        }
    }

    private boolean onSave(EntityPersister persister, Object[] currentState, Object entity) {
        log.info("SpatialLocationListener :: onSave");

        if (entity instanceof SpatialLocated) {
            log.info("SpatialLocationListener :: onSave :: SpatialLocated");

            final SpatialLocated place = (SpatialLocated) entity;
            final Point point = pointFactory.createPointFromSpatialLocation(place.getSpatialLocation());

            setValue(persister, currentState, entity, "point", point);
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

        log.info("PasswordEncoderListener :: onSave :: SpatialLocated :: setEncodedPassword");
        currentState[index] = value;
    }

}