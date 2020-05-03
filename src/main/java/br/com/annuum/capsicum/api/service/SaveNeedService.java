package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.NeedRequest;
import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.DayShiftAvailability;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.repository.NeedRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class SaveNeedService {

    @Autowired
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Autowired
    private NeedRepository needRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Need save(final NeedRequest needRequest) {
        log.info("Start to create an Need for: '{}'", needRequest);
        final Skill skill = findSkillByDescriptionService.find(needRequest.getSkill());

        final Availability availability = new Availability();
        if (nonNull(needRequest.getAvailabilityRequest())) {
            availability.setDayShiftAvailabilities(needRequest.getAvailabilityRequest().getDayShiftAvailabilities()
                .stream()
                .map(dayShft -> modelMapper.map(dayShft, DayShiftAvailability.class))
                .collect(Collectors.toList()));
        }

        log.info("Building Need to persist");
        final Need needToPersist = modelMapper.map(needRequest, Need.class)
            .setSkill(skill)
            .setAvailability(availability);

        log.info("Creating a new Need: '{}'", needToPersist);
        return needRepository.save(needToPersist);
    }
}
