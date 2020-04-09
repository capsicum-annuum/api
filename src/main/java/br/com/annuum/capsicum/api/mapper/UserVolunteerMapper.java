package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.service.FindCauseByDescriptionService;
import br.com.annuum.capsicum.api.service.FindSkillByDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserVolunteerMapper {

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Autowired
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Autowired
    private AdressMapper adressMapper;

    @Autowired
    private LocationCoordinatesMapper locationCoordinatesMapper;

    public UserVolunteer map(UserVolunteerRequest userVolunteerRequest) {

        Address address = adressMapper.map(userVolunteerRequest.getAddressRequest());

        List<Cause> causesThatSupport = userVolunteerRequest.getCauseThatSupport()
                .stream()
                .map(cause -> findCauseByDescriptionService.find(cause))
                .collect(Collectors.toList());

        List<Skill> userSkills = userVolunteerRequest.getUserSkills()
                .stream()
                .map(skill -> findSkillByDescriptionService.find(skill))
                .collect(Collectors.toList());

        UserVolunteer userVolunteer = new UserVolunteer();

        userVolunteer.setUserName(userVolunteerRequest.getUserName());
        userVolunteer.setEmail(userVolunteerRequest.getEmail());
        userVolunteer.setDescription(userVolunteerRequest.getDescription());
        userVolunteer.setAddress(address);
        userVolunteer.setPhone(userVolunteerRequest.getPhone());
        userVolunteer.setProfilePicture(userVolunteerRequest.getProfilePicture());
        userVolunteer.setUserSkills(userSkills);
        userVolunteer.setCauseThatSupport(causesThatSupport);
        userVolunteer.setCreatedAt(LocalDateTime.now());
        userVolunteer.setAllowsBeContactedByOrganization(userVolunteerRequest.getAllowsBeContactedByOrganization());
        userVolunteer.setHasCnh(userVolunteerRequest.getHasCnh());

        if (Objects.nonNull(userVolunteerRequest.getLocationCoordinatesRequest())) {
            userVolunteer.setLocationCoordinates(locationCoordinatesMapper.map(userVolunteerRequest.getLocationCoordinatesRequest()));
        }

        return userVolunteer;
    }
}
