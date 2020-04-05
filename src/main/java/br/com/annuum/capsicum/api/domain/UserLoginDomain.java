package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@SequenceGenerator(name = "user_login_sequence", sequenceName = "user_login_sequence", allocationSize = 0)
@Table(name = "user_login")
public class UserLoginDomain {

    @Id
    @GeneratedValue(generator = "user_login_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String password;
}
