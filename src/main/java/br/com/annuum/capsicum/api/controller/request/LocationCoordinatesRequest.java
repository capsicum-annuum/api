package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LocationCoordinatesRequest {

    @NotBlank(message = "A latitude não pode estar em branco.")
    private Double latitude;

    @NotBlank(message = "A longitude não pode estar em branco.")
    private Double longitude;
}
