package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.enums.PictureType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class PictureRequest {

    @NotEmpty
    private String profilePictureUrl;

    @NotEmpty
    private String profilePictureKey;

    @NotNull
    private PictureType pictureType;

}
