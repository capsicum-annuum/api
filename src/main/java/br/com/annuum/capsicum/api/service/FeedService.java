package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.MovementSimpleResponse;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.mapper.MovementSimpleResponseMapper;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeedService {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private MovementSimpleResponseMapper mapper;

    public Page<MovementSimpleResponse> list(final Long idUser, Pageable pageable) {
        log.info("Mounting feed for user '{}'", idUser);

        final Page<Movement> page = repository.findAll(pageable); // TODO: from feed query

        return page.map(mapper::map);
    }

}
