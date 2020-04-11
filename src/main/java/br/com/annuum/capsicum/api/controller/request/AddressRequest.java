package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
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
