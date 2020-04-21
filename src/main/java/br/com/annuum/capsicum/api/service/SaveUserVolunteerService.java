package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
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
public class SaveUserVolunteerService {

    @Autowired
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    @Autowired
    private SaveAddressService saveAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserVolunteerResponse save(final UserVolunteerRequest userVolunteerRequest) {

        log.info("Start to create an UserVolunteer for: '{}'", userVolunteerRequest);
        final Address address = saveAddressService.saveAddress(userVolunteerRequest.getAddressRequest());

        final List<Cause> causesThatSupport = userVolunteerRequest.getCauseThatSupport()
                .stream()
                .map(cause -> findCauseByDescriptionService.find(cause))
                .collect(Collectors.toList());

        final List<Skill> userSkills = userVolunteerRequest.getUserSkills()
                .stream()
                .map(skill -> findSkillByDescriptionService.find(skill))
                .collect(Collectors.toList());

        final List<DayShiftAvailability> availability = userVolunteerRequest.getAvailability().getDayShiftAvailabilities()
                .stream()
                .map(dayShft -> modelMapper.map(dayShft, DayShiftAvailability.class))
                .collect(Collectors.toList());

        log.info("Building UserVolunteer to persist");
        final UserVolunteer userVolunteer = modelMapper.map(userVolunteerRequest, UserVolunteer.class)
                .setAddress(address)
                .setCauseThatSupport(causesThatSupport)
                .setUserSkills(userSkills)
                .setAvailability(new Availability().setDayShiftAvailabilities(availability));

        userVolunteer.setCreatedAt(LocalDateTime.now());

        log.info("Creating a new UserVolunteer: '{}'", userVolunteer);
        return modelMapper.map(userVolunteerRepository.save(userVolunteer), UserVolunteerResponse.class);
    }
}
