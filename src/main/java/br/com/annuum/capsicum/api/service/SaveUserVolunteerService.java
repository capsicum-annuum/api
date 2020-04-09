package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.mapper.UserVolunteerMapper;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserVolunteerService {

    @Autowired
    private UserVolunteerRepository userVolunteerRepository;

    @Autowired
    private UserVolunteerMapper userVolunteerMapper;

    public void save(UserVolunteerRequest userVolunteerRequest) {
        userVolunteerRepository.save(userVolunteerMapper.map(userVolunteerRequest));
    }
}
