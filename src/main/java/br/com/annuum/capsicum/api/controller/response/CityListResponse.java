package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.dto.CityDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CityListResponse {

    List<CityDto> cities;

}
