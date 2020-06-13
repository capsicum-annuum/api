package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UniqueUserInformationRequest;
import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.exceptions.AlreadyInUseException;
import br.com.annuum.capsicum.api.repository.UserOrganizationRepository;
import br.com.annuum.capsicum.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class UniqueUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrganizationRepository ongRepository;

    public void validate(UniqueUserInformationRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(this::isInvalid);
        userRepository.findByPhone(request.getPhone()).ifPresent(this::isInvalid);

        ofNullable(request.getCnpj()).flatMap(cnpj ->
            ongRepository.findByCnpj(cnpj)).ifPresent(this::isInvalid);
    }

    private void isInvalid(User user) {
        throw new AlreadyInUseException();
    }
}
