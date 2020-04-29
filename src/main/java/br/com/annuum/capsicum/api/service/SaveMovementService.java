package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.converter.EncodableAttributeConverter;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
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
    private EncodableAttributeConverter encodableAttributeConverter;

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    @Autowired
    private SaveAddressService saveAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void save(final MovementRequest movementRequest) {

        final List<Skill> skillsNeeded = movementRequest.getNeedsRequest()
            .stream()
            .map(needRequest -> findSkillByDescriptionService.find(needRequest.getSkill()))
            .collect(Collectors.toList());

        final Movement movement = modelMapper.map(movementRequest, Movement.class)
            .setSkillMatchCode(encodableAttributeConverter.convertToBinaryCode(skillsNeeded));
    }
}
