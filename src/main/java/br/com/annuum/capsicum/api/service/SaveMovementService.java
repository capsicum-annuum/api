package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.controller.response.MovementResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SaveMovementService {

    @Autowired
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

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

        final List<Need> needs = movementRequest.getNeedsRequest().stream()
            .map(needRequest -> saveNeedService.save(needRequest))
            .collect(Collectors.toList());

        final List<Cause> causeThatSupport = movementRequest.getCauseThatSupport().stream()
            .map(cause -> findCauseByDescriptionService.find(cause))
            .collect(Collectors.toList());

        final Movement movement = modelMapper.map(movementRequest, Movement.class)
            .setAddress(address)
            .setCauseThatSupport(causeThatSupport)
            .setNeeds(needs);

        return modelMapper.map(movementRepository.save(movement), MovementResponse.class);
    }
}
