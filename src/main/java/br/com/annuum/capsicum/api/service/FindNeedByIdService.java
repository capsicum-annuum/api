package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.NeedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindNeedByIdService {

    @Autowired
    private NeedRepository needRepository;

    public Need find(final Long id) {
        log.info("Searching for Need with id '{}'", id);
        return needRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Necessidade não encontrada!"));
    }
}
