package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActualLocationRequest {

    @NotNull(message = "A latitude não pode ser nula.")
    private Double actualLatitude;

    @NotNull(message = "A longitude não pode ser nula.")
    private Double actualLongitude;
}
