package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
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

    @OneToOne
    @NotBlank
    private Address address;

    @Embedded
    private LocationCoordinates actualLocationCoordinates;

    private Long profilePictureId;

    private String description;

    @ManyToMany
    private List<Cause> causeThatSupport;
}
