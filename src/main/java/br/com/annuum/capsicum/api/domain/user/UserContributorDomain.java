package br.com.annuum.capsicum.api.domain.user;

import br.com.annuum.capsicum.api.domain.UserSkillsDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "user_contributor")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class UserContributorDomain extends UserDomain {

    @ManyToMany
    private List<UserSkillsDomain> skills;

    private Boolean allowsBeContactedByOrganization;

}
