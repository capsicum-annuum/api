package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.domain.HasEncodedPassword;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordEncoderListenerTest {

    @InjectMocks
    private PasswordEncoderListener target;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EntityPersister persister;

    @Mock
    private EntityMetamodel metamodel;

    @Data
    @Accessors(chain = true)
    class UserWithEncodedPassword implements HasEncodedPassword {

        private Long id;
        private String password;
    }

    @Data
    @Accessors(chain = true)
    class UserWithoutEncodedPassword {

        private Long id;
        private String password;
    }

    @Test
    public void shouldEncodePasswordOnPreInsertWhenEntityImplementsHasEncodedPassword() {

        final Long id = null;
        final String originalPassword = randomAlphanumeric(8);
        final String encryptedPassword = randomAlphanumeric(8);

        final String[] propertyNames = {"password"};
        final Object[] state = {originalPassword};
        final UserWithEncodedPassword entity = new UserWithEncodedPassword().setPassword(originalPassword);
        final PreInsertEvent event = new PreInsertEvent(entity, id, state, persister, null);

        when(persister.getEntityMetamodel()).thenReturn(metamodel);
        when(metamodel.getPropertyNames()).thenReturn(propertyNames);
        when(passwordEncoder.encode(originalPassword)).thenReturn(encryptedPassword);

        final boolean result = target.onPreInsert(event);

        assertFalse(result);
        assertEquals(encryptedPassword, state[0]);
    }

    @Test
    public void shouldNotEncodePasswordOnPreInsertWhenEntityNotImplementsHasEncodedPassword() {

        final Long id = null;
        final String originalPassword = randomAlphanumeric(8);

        final Object[] state = {originalPassword};
        final UserWithoutEncodedPassword entity = new UserWithoutEncodedPassword().setPassword(originalPassword);
        final PreInsertEvent event = new PreInsertEvent(entity, id, state, persister, null);

        final boolean result = target.onPreInsert(event);

        assertFalse(result);
        assertEquals(originalPassword, state[0]);
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    public void shouldEncodePasswordOnPreUpdateWhenEntityImplementsHasEncodedPassword() {

        final Long id = null;
        final String originalPassword = randomAlphanumeric(8);
        final String encryptedPassword = randomAlphanumeric(8);

        final String[] propertyNames = {"password"};
        final Object[] state = {originalPassword};
        final UserWithEncodedPassword entity = new UserWithEncodedPassword().setPassword(originalPassword);
        final PreUpdateEvent event = new PreUpdateEvent(entity, id, state, null, persister, null);

        when(persister.getEntityMetamodel()).thenReturn(metamodel);
        when(metamodel.getPropertyNames()).thenReturn(propertyNames);
        when(passwordEncoder.encode(originalPassword)).thenReturn(encryptedPassword);

        final boolean result = target.onPreUpdate(event);

        assertFalse(result);
        assertEquals(encryptedPassword, state[0]);
    }

    @Test
    public void shouldNotEncodePasswordOnPreUpdateWhenEntityNotImplementsHasEncodedPassword() {

        final Long id = null;
        final String originalPassword = randomAlphanumeric(8);

        final Object[] state = {originalPassword};
        final UserWithoutEncodedPassword entity = new UserWithoutEncodedPassword().setPassword(originalPassword);
        final PreUpdateEvent event = new PreUpdateEvent(entity, id, state, null, persister, null);

        final boolean result = target.onPreUpdate(event);

        assertFalse(result);
        assertEquals(originalPassword, state[0]);
        verify(passwordEncoder, never()).encode(any());
    }

}