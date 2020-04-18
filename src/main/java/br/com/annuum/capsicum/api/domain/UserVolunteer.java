package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static br.com.annuum.capsicum.api.domain.enums.Profile.VOLUNTEER;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserVolunteer extends AbstractUser {

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

    @NotEmpty
    @ManyToMany
    private List<Skill> userSkills;

    @NotNull
    @ColumnDefault("false")
    private Boolean hasCnh;

    @Override
    public Profile getProfile() {
        return VOLUNTEER;
    }
}
