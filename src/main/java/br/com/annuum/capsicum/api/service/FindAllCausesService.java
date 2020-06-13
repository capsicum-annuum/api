package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.CauseListResponse;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindAllCausesService {

    @Autowired
    private CauseRepository causeRepository;

    public CauseListResponse find() {
        log.info("Searching for all Causes.");
        return new CauseListResponse()
            .setCauseDtoList(causeRepository.retrieveAllCausesAsCausesDto());
    }

}
