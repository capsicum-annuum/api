package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressRequest {

    @NotBlank(message = "The Address GooglePlaceId must not be null or empty.")
    private Long googlePlaceAddressId;

    @NotBlank(message = "The City GooglePlaceId must not be null or empty.")
    private Long googlePlaceCityId;

    @Nullable
    private String district;

    @Nullable
    private String streetName;

    @Nullable
    private String addressNumber;

    @Nullable
    private String complement;
}
