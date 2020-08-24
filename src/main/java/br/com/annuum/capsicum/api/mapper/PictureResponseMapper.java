package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.response.PictureResponse;
import br.com.annuum.capsicum.api.domain.Picture;
import org.springframework.stereotype.Component;

@Component
public class PictureResponseMapper implements Mapper<Picture, PictureResponse> {

    @Override
    public PictureResponse map(Picture picture) {
        return new PictureResponse()
            .setPictureUrl(picture.getPictureUrl())
            .setPictureRelevance(picture.getPictureRelevance());
    }

}
