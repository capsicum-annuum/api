package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.List;

@Getter
public class UserVolunteerRequest {

    @NotBlank(message = "User name must not be null or empty.")
    private String userName;

    @NotBlank(message = "Email must not be or empty.")
    @Email(message = "Email invalid.")
    private String email;

    @NotBlank(message = "Password must not be or empty.")
    private String password;

    @Nullable
    private String phone;

    @NotBlank(message = "Address must not be null or empty.")
    private AddressRequest addressRequest;

    @Nullable
    private LocationCoordinatesRequest locationCoordinatesRequest;

    @Nullable
    private Blob profilePicture;

    @Nullable
    private String description;

    @NotBlank(message = "Causes must not be null or empty.")
    private List<String> causeThatSupport;

    @NotBlank(message = "AllowsBeContactedByOrganization must not be null or empty.")
    private Boolean allowsBeContactedByOrganization;

    @NotBlank(message = "HasCNH must not be null or empty.")
    private Boolean hasCnh;

    @NotBlank(message = "User skills must not be null or empty.")
    private List<String> userSkills;
}
