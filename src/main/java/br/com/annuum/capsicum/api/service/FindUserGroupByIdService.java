package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindUserGroupByIdService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    public UserGroup find(final Long id) {
        log.info("Searching for UserGroup with id '{}'", id);
        return userGroupRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("UserGroup não encontrado!"));
    }
}
