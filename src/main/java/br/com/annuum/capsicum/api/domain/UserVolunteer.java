package br.com.annuum.capsicum.api.domain;

import static br.com.annuum.capsicum.api.domain.enums.Profile.VOLUNTEER;

import br.com.annuum.capsicum.api.converter.AvailabilityConverter;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

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
  private List<Cause> causeThatSupport;

  @ManyToMany
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
