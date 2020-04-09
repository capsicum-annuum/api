package br.com.annuum.capsicum.api.controller.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressRequest {

    @NotBlank(message = "User name must not be null or empty.")
    private Long idCity;

    @NotBlank(message = "User name must not be null or empty.")
    private String district;

    @Nullable
    private String streetName;

    @Nullable
    private String addressNumber;

    @Nullable
    private String complement;
}
