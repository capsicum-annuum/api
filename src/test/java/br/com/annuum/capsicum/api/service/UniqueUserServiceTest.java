package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UniqueUserInformationRequest;
import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.exceptions.AlreadyInUseException;
import br.com.annuum.capsicum.api.repository.UserOrganizationRepository;
import br.com.annuum.capsicum.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniqueUserServiceTest {

    @InjectMocks
    private UniqueUserService uniqueUserService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserOrganizationRepository ongRepository;

    @Test
    public void shouldThrowErrorWhenEmailAlreasyInUse() {
        final UniqueUserInformationRequest request = createRequest();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(of(mock(User.class)));

        assertThrows(AlreadyInUseException.class, () -> uniqueUserService.validate(request));
    }

    @Test
    public void shouldThrowErrorWhenPhoneAlreasyInUse() {
        final UniqueUserInformationRequest request = createRequest();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(empty());
        when(userRepository.findByPhone(request.getPhone())).thenReturn(of(mock(User.class)));

        assertThrows(AlreadyInUseException.class, () -> uniqueUserService.validate(request));
    }

    @Test
    public void shouldThrowErrorWhenCnpjAlreasyInUse() {
        final UniqueUserInformationRequest request = createRequest();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(empty());
        when(userRepository.findByPhone(request.getPhone())).thenReturn(empty());
        when(ongRepository.findByCnpj(request.getCnpj())).thenReturn(of(mock(UserOrganization.class)));

        assertThrows(AlreadyInUseException.class, () -> uniqueUserService.validate(request));
    }

    @Test
    public void shouldNotThrowErrorWhenInformationNotInUse() {
        final UniqueUserInformationRequest request = createRequest();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(empty());
        when(userRepository.findByPhone(request.getPhone())).thenReturn(empty());
        when(ongRepository.findByCnpj(request.getCnpj())).thenReturn(empty());

        uniqueUserService.validate(request);
    }

    @Test
    public void shouldNotThrowErrorWhenCnpjHasNotBeenProvided() {
        final UniqueUserInformationRequest request = createRequest()
            .setCnpj(null);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(empty());
        when(userRepository.findByPhone(request.getPhone())).thenReturn(empty());

        uniqueUserService.validate(request);

        verify(ongRepository, never()).findByCnpj(anyString());
    }

    private UniqueUserInformationRequest createRequest() {
        final String email = randomAlphanumeric(10);
        final String phone = randomNumeric(11);
        final String cnpj = randomNumeric(14);

        return new UniqueUserInformationRequest()
            .setCnpj(cnpj)
            .setEmail(email)
            .setPhone(phone);
    }

}
