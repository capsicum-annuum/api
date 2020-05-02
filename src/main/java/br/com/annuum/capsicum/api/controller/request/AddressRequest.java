package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AddressRequest {

    @NotNull(message = "A cidade precisa ser informada.")
    private CityRequest cityRequest;

    @NotNull(message = "A localização espacial precisa ser informada.")
    private Double latitude;

    @NotNull
    private Double longitude;

    @Nullable
    private String googlePlaceAddressIdentifier;

    @Nullable
    private String district;

    @Nullable
    private String streetName;

    @Nullable
    private String addressNumber;

    @Nullable
    private String complement;
}
