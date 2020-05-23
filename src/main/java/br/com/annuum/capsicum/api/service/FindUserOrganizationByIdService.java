package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.UserOrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindUserOrganizationByIdService {

    @Autowired
    private UserOrganizationRepository userOrganizationRepository;

    public UserOrganization find(final Long id) {
        log.info("Searching for UserOrganization with id '{}'", id);
        return userOrganizationRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Organização não encontrado!"));
    }
}
