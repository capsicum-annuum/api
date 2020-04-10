package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class UserVolunteerRequest {

    @NotBlank(message = "O nome do usuário não pode estar em branco.")
    private String name;

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O email informado é inválido.")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;

    @NotBlank(message = "A lista de Causas não pode estar em branco.")
    private List<String> causeThatSupport;

    @NotBlank(message = "A lista de Habilidades não pode estar em branco.")
    private List<String> userSkills;

    @NotBlank(message = "É preciso informar se usuário possui CNH.")
    private Boolean hasCnh;

    @NotBlank(message = "O objeto Endereço não pode estar em branco.")
    private AddressRequest addressRequest;

    @Nullable
    private LocationCoordinatesRequest actualLocationCoordinatesRequest;

    @Nullable
    private String phone;

    @Nullable
    private Long profilePictureId;

    @Nullable
    private String description;

}
