package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.converter.AvailabilityConverter;
import br.com.annuum.capsicum.api.converter.EncodableAttributeConverter;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
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
    private LocationCoordinates actualLocationCoordinates;

    private Long profilePictureId;

    private String description;

    @ManyToMany
    @Convert(converter = EncodableAttributeConverter.class)
    private List<Cause> causeThatSupport;

    @ManyToMany
    @Convert(converter = EncodableAttributeConverter.class)
    private List<Skill> userSkills;

    @Convert(converter = AvailabilityConverter.class)
    private Availability availability;

    @ColumnDefault("false")
    private Boolean hasCnh;

    @Override
    public Profile getProfile() {
        return VOLUNTEER;
    }

}
