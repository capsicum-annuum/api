package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressRequest {

    @NotBlank(message = "O identificador GooglePlaceId não pode estar em branco para a cidade.")
    private String googlePlaceCityId;

    @NotBlank(message = "As coordenadas mais precisas do endereço precisam ser informadas.")
    private LocationCoordinatesRequest locationCoordinatesRequest;

    @Nullable
    private String googlePlaceAddressId;

    @Nullable
    private String district;

    @Nullable
    private String streetName;

    @Nullable
    private String addressNumber;

    @Nullable
    private String complement;
}
