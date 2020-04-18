package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.Profile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static br.com.annuum.capsicum.api.domain.enums.Profile.GROUP;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserGroup extends AbstractUser {

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

    @Override
    public Profile getProfile() {
        return GROUP;
    }
}
