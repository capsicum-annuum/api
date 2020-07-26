package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.request.PictureRequest;
import br.com.annuum.capsicum.api.domain.Picture;
import org.springframework.stereotype.Component;

@Component
public class PictureMapper implements Mapper<PictureRequest, Picture> {

    @Override
    public Picture map(PictureRequest pictureRequest) {
        return new Picture()
            .setPictureKey(pictureRequest.getPictureKey())
            .setPictureUrl(pictureRequest.getPictureUrl())
            .setPictureRelevance(pictureRequest.getPictureRelevance());
    }

}
