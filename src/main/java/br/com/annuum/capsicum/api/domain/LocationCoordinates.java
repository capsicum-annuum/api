package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Embeddable
public class LocationCoordinates {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
