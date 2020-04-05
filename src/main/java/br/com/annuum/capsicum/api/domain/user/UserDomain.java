package br.com.annuum.capsicum.api.domain.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@MappedSuperclass
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
public abstract class UserDomain {

    @Id
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
