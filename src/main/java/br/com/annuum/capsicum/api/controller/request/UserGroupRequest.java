package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.annotation.Phone;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserGroupRequest {

    @NotBlank(message = "O nome do usuário não pode estar em branco.")
    private String name;

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O email informado é inválido.")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;

    private String mission;

    @NotEmpty(message = "A lista de Causas não pode estar vazia.")
    private List<Long> causeThatSupport;

    @NotNull(message = "O objeto Endereço não pode ser nulo.")
    private AddressRequest addressRequest;

    @Nullable
    @Phone(message = "O telefone informado é inválido.")
    private String phone;

    @Nullable
    private List<PictureRequest> pictureRequests;

    @Nullable
    private String description;

    @Nullable
    private String facebookUrl;

    @Nullable
    private String instagramUrl;

    @Nullable
    private String twitterUrl;

}
