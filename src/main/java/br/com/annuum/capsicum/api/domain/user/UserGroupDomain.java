package br.com.annuum.capsicum.api.domain.user;

import br.com.annuum.capsicum.api.domain.CausesDomain;
import br.com.annuum.capsicum.api.domain.UserAddressDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "user_group")
public class UserGroupDomain extends UserDomain {

    @NotBlank
    private String groupName;

    @Email
    private String email;

    private String phone;

    @OneToOne
    private UserAddressDomain userAddress;

    @Lob
    private Blob profilePicture;

    private String description;

    @ManyToMany
    private List<CausesDomain> causesThatSupport;

    private String groupMission;
}
