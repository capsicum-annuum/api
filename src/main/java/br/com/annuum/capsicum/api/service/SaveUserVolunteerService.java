package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SaveUserVolunteerService {

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    @Autowired
    private FindCityByGooglePlaceIdService findCityByGooglePlaceIdService;

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Autowired
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Autowired
    private ModelMapper modelMapper;

    public void save(UserVolunteerRequest userVolunteerRequest) {

        City city = findCityByGooglePlaceIdService.find(userVolunteerRequest.getAddressRequest().getGooglePlaceCityId());

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

        if (Objects.nonNull(userVolunteerRequest.getActualLocationCoordinatesRequest())) {
            userVolunteer.setActualLocationCoordinates(modelMapper.map(userVolunteerRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class));
        }

        userVolunteerRepository.save(userVolunteer);
    }
}
