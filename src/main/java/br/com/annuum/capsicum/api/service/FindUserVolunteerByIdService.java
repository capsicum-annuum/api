package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindUserVolunteerByIdService {

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    public UserVolunteer find(final Long id) {
        log.info("Searching for UserVolunteer with id '{}'", id);
        return userVolunteerRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Voluntário não encontrado!"));
    }
}
