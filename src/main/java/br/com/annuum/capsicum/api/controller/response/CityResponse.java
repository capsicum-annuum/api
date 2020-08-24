package br.com.annuum.capsicum.api.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CityResponse {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

}
