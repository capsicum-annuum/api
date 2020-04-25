package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class LocationCoordinatesRequest {

    @NotNull(message = "A latitude não pode estar em branco.")
    private Double latitude;

    @NotNull(message = "A longitude não pode estar em branco.")
    private Double longitude;
}
