package br.com.annuum.capsicum.api.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Data
@Embeddable
public class LocationCoordinates {

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;
}
