package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.FederatedUnity;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.FederatedUnityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindFederatedUnityByIdService {

    @Autowired
    private FederatedUnityRepository federatedUnityRepository;

    public FederatedUnity find(final Long idState) {
        log.info("Searching for FederatedUnity with id '{}'", idState);
        return federatedUnityRepository.findById(idState)
            .orElseThrow(() -> new RegisterNotFoundException("Estado não encontrado!"));
    }

}
