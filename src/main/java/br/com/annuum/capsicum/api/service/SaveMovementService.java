package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.controller.response.MovementResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SaveMovementService {

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

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

    @Transactional
    public MovementResponse save(final MovementRequest movementRequest) {

        log.info("Start to create an UserOrganization for: '{}'", movementRequest);
        final Address address = saveAddressService.saveAddress(movementRequest.getAddressRequest());

        final AbstractUser abstractUser = findUserByIdService.find(movementRequest.getUserAuthorId());

        final List<Need> needs = movementRequest.getNeedsRequest().stream()
            .map(needRequest -> saveNeedService.save(needRequest))
            .collect(Collectors.toList());

        final List<Cause> causeThatSupport = movementRequest.getCauseThatSupport().stream()
            .map(cause -> findCauseByDescriptionService.find(cause))
            .collect(Collectors.toList());

        final Movement movement = new Movement()
            .setUserAuthor(abstractUser)
            .setAddress(address)
            .setCauseThatSupport(causeThatSupport)
            .setNeeds(needs)
            .setDateTimeStart(movementRequest.getDateTimeStart())
            .setDateTimeEnd(movementRequest.getDateTimeEnd())
            .setPictureId(movementRequest.getPictureId())
            .setTitle(movementRequest.getTitle())
            .setDescription(movementRequest.getDescription())
            .setCreatedAt(LocalDateTime.now());

        return modelMapper.map(movementRepository.save(movement), MovementResponse.class);
    }
}