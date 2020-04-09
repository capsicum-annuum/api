package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LocationCoordinatesRequest {

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;
}
