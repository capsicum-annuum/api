package br.com.annuum.capsicum.api.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVolunteerResponse {

    private String email;

    private String name;

    private String description;
}
