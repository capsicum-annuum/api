package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.enums.State;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CityRequest {

    @NotBlank(message = "O identificador GooglePlaceId não pode estar em branco para a cidade.")
    private String googlePlaceCityId;

    @NotBlank(message = "O nome da cidade não pode estar em branco.")
    private String name;

    @NotNull(message = "O Estado deve ser informado")
    private State state;
}
