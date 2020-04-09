package br.com.annuum.capsicum.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserVolunteer extends User {

    @NotNull
    @ColumnDefault("false")
    private Boolean allowsBeContactedByOrganization;

    @NotNull
    @ColumnDefault("false")
    private Boolean hasCnh;

    @ManyToMany
    private List<Skill> userSkills;
}
