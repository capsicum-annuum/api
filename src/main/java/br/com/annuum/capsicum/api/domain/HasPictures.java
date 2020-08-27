package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.PictureRelevance;

public interface HasPictures {

    Picture getPictureByRelevance(PictureRelevance pictureRelevance);

}
