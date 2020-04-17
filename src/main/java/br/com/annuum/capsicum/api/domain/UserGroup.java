package br.com.annuum.capsicum.api.domain;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @OneToOne
    private Address address;

    @Embedded
    private LocationCoordinates actualLocationCoordinates;

    @Column(columnDefinition = "geography(POINT, 4326)")
    private Geometry geography;

    private Long profilePictureId;

    private String description;

    @NotEmpty
    @ManyToMany
    private List<Cause> causeThatSupport;
}
