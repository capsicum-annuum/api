package br.com.annuum.capsicum.api.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Data
@MappedSuperclass
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
public abstract class User {

    @Id
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String phone;

    @OneToOne
    private UserAddress userAddress;

    @Embedded
    private LocationCoordinates locationCoordinates;

    @Lob
    private Blob profilePicture;

    private String description;

    @ManyToMany
    private List<Cause> causeThatSupport;

    @NotNull
    private LocalDateTime createdAt;

    protected User() {
    }
}
