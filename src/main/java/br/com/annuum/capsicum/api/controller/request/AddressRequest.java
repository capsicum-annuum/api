package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AddressRequest {

    @NotNull(message = "A latitude precisa ser informada.")
    private Double latitude;

    @NotNull(message = "A longitude precisa ser informada.")
    private Double longitude;

    @NotNull
    private String cityName;

    @NotNull
    private String federatedUnityAcronym;

    @Nullable
    private String district;

    @Nullable
    private String streetName;

    @Nullable
    private String addressNumber;

    @Nullable
    private String complement;
}
