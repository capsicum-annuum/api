package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter
public class AddressRequest {

    @NotNull(message = "O objeto cidade não pode estar em branco.")
    private CityRequest cityRequest;

    @NotNull(message = "As coordenadas mais precisas do endereço precisam ser informadas.")
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
