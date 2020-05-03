package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class NeedRequest {

    @NotBlank(message = "A habilidade não pode estar vazia.")
    private String skill;

    @NotNull(message = "O id do usuário autor não pode ser nulo.")
    private Integer quantity;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String description;

    @Nullable
    private AvailabilityRequest availabilityRequest;

}
