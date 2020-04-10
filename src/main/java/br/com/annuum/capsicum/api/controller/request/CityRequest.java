package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.enums.State;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CityRequest {

    @NotBlank(message = "O identificador GooglePlaceId não pode estar em branco para a cidade.")
    private String googlePlaceCityId;

    @NotBlank
    private String name;

    @NotBlank
    private State state;
}
