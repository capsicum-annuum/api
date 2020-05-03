package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.converter.AvailabilityConverter;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.listener.AttributeEncodeListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static br.com.annuum.capsicum.api.domain.enums.Profile.VOLUNTEER;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@EntityListeners(AttributeEncodeListener.class)
@Entity
public class UserVolunteer extends AbstractUser {

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    @ManyToMany
    private List<Cause> causeThatSupport;

    @NotBlank
    private String causeMatchCode;

    @NotNull
    @ManyToMany
    private List<Skill> userSkills;

    @NotBlank
    private String skillMatchCode;

    @NotNull
    @Convert(converter = AvailabilityConverter.class)
    private Availability availability;

    private String phone;

    private Long profilePictureId;

    private String description;

    private String facebookUrl;

    private String instagramUrl;

    private String twitterUrl;

    @Override
    public Profile getProfile() {
        return VOLUNTEER;
    }

}
