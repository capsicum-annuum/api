package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserOrganizationRequest {

    @NotBlank(message = "O nome do usuário não pode estar em branco.")
    private String name;

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O email informado é inválido.")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;

    @NotBlank(message = "O CNPJ não pode estar em branco.")
    @CNPJ(message = "É preciso informar um CNPJ válido.")
    private String cnpj;

    private String mission;

    @NotEmpty(message = "A lista de Causas não pode estar vazia.")
    private List<Long> causeThatSupport;

    @NotNull(message = "O objeto Endereço não pode ser nulo.")
    private AddressRequest addressRequest;

    @NotBlank(message = "A descrição não pode estar em branco.")
    private String description;

    @Nullable
    private String phone;

    @Nullable
    private String profilePictureUrl;

    @Nullable
    private String backgroundPictureUrl;

    @Nullable
    private String webSiteUrl;

    @Nullable
    private String facebookUrl;

    @Nullable
    private String instagramUrl;

    @Nullable
    private String twitterUrl;
}
