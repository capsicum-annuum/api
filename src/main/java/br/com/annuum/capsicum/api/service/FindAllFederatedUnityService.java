package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.FederatedUnityResponse;
import br.com.annuum.capsicum.api.domain.FederatedUnity;
import br.com.annuum.capsicum.api.mapper.FederatedUnityMapper;
import br.com.annuum.capsicum.api.repository.FederatedUnityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class FindAllFederatedUnityService {

    @Autowired
    private FederatedUnityRepository federatedUnityRepository;

    @Resource
    private FederatedUnityMapper federatedUnityMapper;

    public List<FederatedUnityResponse> find() {
        log.info("Searching for all FederatedUnity");
        List<FederatedUnity> federatedUnities = (List<FederatedUnity>) federatedUnityRepository.findAll();
        return federatedUnityMapper.toFederatedUnityList(federatedUnities);
    }
}
