package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AddressRequest {

    @NotNull(message = "O id cidade precisa ser informado.")
    private Long idCity;

    @NotNull(message = "A latitude precisa ser informada.")
    private Double latitude;

    @NotNull(message = "A longitude precisa ser informada.")
    private Double longitude;

    @Nullable
    private String district;

    @Nullable
    private String streetName;

    @Nullable
    private String addressNumber;

    @Nullable
    private String complement;
}
