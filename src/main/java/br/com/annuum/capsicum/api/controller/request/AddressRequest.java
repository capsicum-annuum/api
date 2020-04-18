package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AddressRequest {

    @NotNull(message = "O objeto cidade não pode estar em branco.")
    private CityRequest cityRequest;

    @NotNull(message = "A latitude do endereço precisa ser informada.")
    private Double latitude;

    @NotNull(message = "A longitude do endereço precisa ser informado.")
    private Double longitude;

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
