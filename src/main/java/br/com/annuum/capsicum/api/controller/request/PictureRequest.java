package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.enums.PictureRelevance;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class PictureRequest {

    @NotEmpty(message = "A url da imagem não poder ser vazia.")
    private String profilePictureUrl;

    @NotEmpty(message = "A key da imagem não pode ser vazia.")
    private String profilePictureKey;

    @NotNull(message = "A relevância da imagem não pode ser nula.")
    private PictureRelevance pictureRelevance;

}
