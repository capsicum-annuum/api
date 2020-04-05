package br.com.annuum.capsicum.api.domain.user;

import br.com.annuum.capsicum.api.domain.CausesDomain;
import br.com.annuum.capsicum.api.domain.UserAddressDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "user_organization")
public class UserOrganizationDomain extends UserDomain {

    @NotBlank
    private String organizationName;

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

    @NotBlank
    @CNPJ
    private String cnpj;

    private String organizationMission;

}
