package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserOrganization extends User {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @CNPJ
    private String cnpj;

    private String mission;

    private String phone;

    @NotNull
    @OneToOne
    private Address address;

    @Embedded
    private ActualLocation actualLocation;

    private Long profilePictureId;

    private String description;

    @NotEmpty
    @ManyToMany
    private List<Cause> causeThatSupport;
}
