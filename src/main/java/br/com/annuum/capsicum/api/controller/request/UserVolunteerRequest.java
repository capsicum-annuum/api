package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class UserVolunteerRequest {

    @NotBlank(message = "User name must not be null or empty.")
    private String name;

    @NotBlank(message = "Email must not be null or empty.")
    @Email(message = "Email invalid.")
    private String email;

    @NotBlank(message = "Password must not be null or empty.")
    private String password;

    @Nullable
    private String phone;

    @NotBlank(message = "Address must not be null or empty.")
    private AddressRequest addressRequest;

    @Nullable
    private LocationCoordinatesRequest actualLocationCoordinatesRequest;

    @Nullable
    private Long profilePictureId;

    @Nullable
    private String description;

    @NotBlank(message = "Causes must not be null or empty.")
    private List<String> causeThatSupport;

    @NotBlank(message = "HasCNH must not be null or empty.")
    private Boolean hasCnh;

    @NotBlank(message = "User skills must not be null or empty.")
    private List<String> userSkills;
}
