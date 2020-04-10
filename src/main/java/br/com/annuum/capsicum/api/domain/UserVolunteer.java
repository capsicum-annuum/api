package br.com.annuum.capsicum.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserVolunteer extends User {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

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

    @NotNull
    @ColumnDefault("false")
    private Boolean hasCnh;

    @ManyToMany
    private List<Skill> userSkills;
}
