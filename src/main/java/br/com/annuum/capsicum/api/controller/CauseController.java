package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.CauseListResponse;
import br.com.annuum.capsicum.api.service.FindAllCausesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cause")
public class CauseController {

    @Autowired
    private FindAllCausesService findAllCausesService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CauseListResponse findAllCauses() {
        return findAllCausesService.find();
    }

}
