package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UniqueUserInformationRequest;
import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.mapper.AvailabilityMapper;
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
public class SaveUserVolunteerService {

    @Autowired
    private  UniqueUserService uniqueUserService;

    @Autowired
    private FindSkillByIdService findSkillByIdService;

    @Autowired
    private FindCauseByIdService findCauseByIdService;

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    @Autowired
    private SaveAddressService saveAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AvailabilityMapper availabilityMapper;

    @Transactional
    public UserVolunteerResponse save(final UserVolunteerRequest userVolunteerRequest) {
        log.info("Start to create an UserVolunteer for: '{}'", userVolunteerRequest);

        uniqueUserService.validate(new UniqueUserInformationRequest()
            .setEmail(userVolunteerRequest.getEmail())
            .setPhone(userVolunteerRequest.getPhone()));

        final Address address = saveAddressService.save(userVolunteerRequest.getAddressRequest());

        final List<Cause> causesThatSupport = userVolunteerRequest.getCauseThatSupport()
            .stream()
            .map(cause -> findCauseByIdService.find(cause))
            .collect(Collectors.toList());

        final List<Skill> userSkills = userVolunteerRequest.getUserSkills()
            .stream()
            .map(skill -> findSkillByIdService.find(skill))
            .collect(Collectors.toList());

        log.info("Building UserVolunteer to persist");
        final UserVolunteer userVolunteer = modelMapper.map(userVolunteerRequest, UserVolunteer.class)
            .setAddress(address)
            .setCauseThatSupport(causesThatSupport)
            .setUserSkills(userSkills)
            .setAvailability(availabilityMapper.map(userVolunteerRequest.getAvailability()));

        log.info("Creating a new UserVolunteer: '{}'", userVolunteer);
        return modelMapper.map(userVolunteerRepository.save(userVolunteer), UserVolunteerResponse.class);
    }
}
