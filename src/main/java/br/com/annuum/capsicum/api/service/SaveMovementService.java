package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.controller.response.MovementResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.mapper.PictureMapper;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import br.com.annuum.capsicum.api.validator.MovementAuthorEvaluationDebitsValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class SaveMovementService {

    @Autowired
    private FindCauseByIdService findCauseByIdService;

    @Autowired
    private FindUserByIdService findUserByIdService;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private SaveNeedService saveNeedService;

    @Autowired
    private SaveAddressService saveAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private MovementAuthorEvaluationDebitsValidator movementAuthorEvaluationDebitsValidator;

    @Transactional
    public MovementResponse save(final Long idUserAuthenticated, final MovementRequest movementRequest) {

        log.info("Start to create a Movement for: '{}'", movementRequest);
        final Address address = saveAddressService.save(movementRequest.getAddressRequest());

        final AbstractUser abstractUser = findUserByIdService.find(idUserAuthenticated);

        movementAuthorEvaluationDebitsValidator.validate(idUserAuthenticated);

        final List<Need> needs = movementRequest.getNeedsRequest()
            .stream()
            .map(needRequest -> saveNeedService.save(needRequest))
            .collect(Collectors.toList());

        final List<Cause> causeThatSupport = movementRequest.getCauseThatSupport().stream()
            .map(cause -> findCauseByIdService.find(cause))
            .collect(Collectors.toList());

        log.info("Building Movement to persist");
        final Movement movement = new Movement()
            .setUserAuthor(abstractUser)
            .setAddress(address)
            .setCauseThatSupport(causeThatSupport)
            .setNeeds(needs)
            .setDateTimeStart(movementRequest.getDateTimeStart())
            .setDateTimeEnd(movementRequest.getDateTimeEnd())
            .setTitle(movementRequest.getTitle())
            .setDescription(movementRequest.getDescription())
            .setMovementStatus(MovementStatus.ACTIVE);

        if (nonNull(movementRequest.getPictureRequests())) {
            movement.setPictures(pictureMapper.map(movementRequest.getPictureRequests()));
        }

        log.info("Creating a new Movement: '{}'", movement);
        return modelMapper.map(movementRepository.save(movement), MovementResponse.class);
    }
}
