package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.converter.AvailabilityConverter;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.listener.UserVolunteerListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static br.com.annuum.capsicum.api.domain.enums.Profile.VOLUNTEER;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@EntityListeners(UserVolunteerListener.class)
@Entity
public class UserVolunteer extends AbstractUser {

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    @ManyToMany
    private List<Cause> causeThatSupport;

    private String causeMatchCode;

    @NotNull
    @ManyToMany
    private List<Skill> userSkills;

    private String skillMatchCode;

    @NotNull
    @Convert(converter = AvailabilityConverter.class)
    private Availability availability;

    @Size(max = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @Size(max = 100)
    private String facebookUrl;

    @Size(max = 100)
    private String instagramUrl;

    @Size(max = 100)
    private String twitterUrl;

    @Override
    public Profile getProfile() {
        return VOLUNTEER;
    }

}
