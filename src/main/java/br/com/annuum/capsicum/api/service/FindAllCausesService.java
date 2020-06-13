package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.dto.CauseDto;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllCausesService {

    @Autowired
    private CauseRepository causeRepository;

    public List<CauseDto> find() {
        log.info("Searching for all Causes.");
        return causeRepository.retrieveAllCausesAsCausesDto();
    }

}
