package br.com.annuum.capsicum.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CauseDto implements java.io.Serializable {

    private Long id;

    private String description;

}
