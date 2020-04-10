package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class SaveUserVolunteerService {

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    @Autowired
    private FindOrCreateNewCityService findOrCreateNewCityService;

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Autowired
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void save(UserVolunteerRequest userVolunteerRequest) {

        City city = findOrCreateNewCityService.findOrCreateNewCity(userVolunteerRequest.getAddressRequest().getCityRequest());

        Address address = modelMapper.map(userVolunteerRequest.getAddressRequest(), Address.class)
                .setCity(city);

        List<Cause> causesThatSupport = userVolunteerRequest.getCauseThatSupport()
                .stream()
                .map(cause -> findCauseByDescriptionService.find(cause))
                .collect(Collectors.toList());

        List<Skill> userSkills = userVolunteerRequest.getUserSkills()
                .stream()
                .map(skill -> findSkillByDescriptionService.find(skill))
                .collect(Collectors.toList());

        UserVolunteer userVolunteer = modelMapper.map(userVolunteerRequest, UserVolunteer.class)
                .setAddress(address)
                .setCauseThatSupport(causesThatSupport)
                .setUserSkills(userSkills);

        if (nonNull(userVolunteerRequest.getActualLocationCoordinatesRequest())) {
            userVolunteer.setActualLocationCoordinates(modelMapper.map(userVolunteerRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class));
        }

        userVolunteerRepository.save(userVolunteer);
    }
}
