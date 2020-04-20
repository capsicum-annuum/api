package br.com.annuum.capsicum.api.service;

import static java.util.Objects.nonNull;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.DayShiftAvailability;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    if (nonNull(userVolunteerRequest.getActualLocationCoordinatesRequest())) {
      log.info("Getting LocationCoordinates from user");
      userVolunteer.setActualLocationCoordinates(modelMapper.map(userVolunteerRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class));
    }

    log.info("Creating a new UserVolunteer: '{}'", userVolunteer);
    return modelMapper.map(userVolunteerRepository.save(userVolunteer), UserVolunteerResponse.class);
  }
}
