package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Embeddable
public class LocationCoordinates {

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;
}
