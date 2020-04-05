package br.com.annuum.capsicum.api.domain.user;

import br.com.annuum.capsicum.api.domain.CausesDomain;
import br.com.annuum.capsicum.api.domain.UserAddressDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Blob;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserDomain {

    @Id
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Email
    private String email;

    private String phone;

    @OneToOne
    private UserAddressDomain userAddress;

    @Lob
    private Blob userImage;

    private String description;

    @ManyToMany
    private List<CausesDomain> causes;

    private UserType userType;
}
