package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.annotation.Phone;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class UniqueUserInformationRequest {

    @Phone(message = "O telefone informado é inválido.")
    @NotBlank
    private String phone;

    @Email(message = "O email informado é inválido.")
    @NotBlank
    private String email;

    @CNPJ(message = "É preciso informar um CNPJ válido.")
    @Nullable
    private String cnpj;

}
