package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.Profile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static br.com.annuum.capsicum.api.domain.enums.Profile.ORGANIZATION;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
public class UserOrganization extends AbstractUser {

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotNull
    @OneToOne
    private Address address;

    @NotEmpty
    @ManyToMany
    private List<Cause> causeThatSupport;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @Size(max = 1000)
    private String mission;

    @Size(max = 100)
    private String webSiteUrl;

    @Size(max = 100)
    private String facebookUrl;

    @Size(max = 100)
    private String instagramUrl;

    @Size(max = 100)
    private String twitterUrl;

    private Long profilePictureId;

    private Long backgroundPictureId;

    @Override
    public Profile getProfile() {
        return ORGANIZATION;
    }
}
