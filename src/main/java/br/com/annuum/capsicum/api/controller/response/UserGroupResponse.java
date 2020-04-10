package br.com.annuum.capsicum.api.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserGroupResponse {

    private String email;

    private String userName;

    private String description;

}
