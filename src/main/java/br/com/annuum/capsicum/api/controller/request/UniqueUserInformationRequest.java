package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.annotation.Phone;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class UniqueUserInformationRequest {

    @Phone
    @NotBlank
    private String phone;

    @Email
    @NotBlank
    private String email;

    @CNPJ
    private String cnpj;

}
