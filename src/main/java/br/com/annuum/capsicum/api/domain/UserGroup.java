package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserGroup extends User {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

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
