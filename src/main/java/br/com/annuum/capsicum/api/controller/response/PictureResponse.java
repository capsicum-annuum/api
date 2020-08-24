package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.enums.PictureRelevance;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class PictureResponse {

    private Long id;

    private String pictureUrl;

    private PictureRelevance pictureRelevance;
}
