package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.response.MovementDetailsResponse;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.enums.PictureRelevance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class MovementDetailResponseMapper implements Mapper<Movement, MovementDetailsResponse> {

    @Autowired
    private NeedDetailResponseMapper needMapper;

    @Autowired
    private PictureResponseMapper pictureResponseMapper;

    @Override
    public MovementDetailsResponse map(Movement movement) {
        return new MovementDetailsResponse()
            .setId(movement.getId())
            .setTitle(movement.getTitle())
            .setDescription(movement.getDescription())
            .setAuthor(movement.getUserAuthor().getName())
            .setAuthorPictureUrl(movement.getUserAuthor().getPictureByRelevance(PictureRelevance.PRIMARY).getPictureUrl())
            .setPictures(pictureResponseMapper.map(movement.getPictures()))
            .setStart(movement.getDateTimeStart())
            .setEnd(movement.getDateTimeEnd())
            .setCityName(movement.getAddress().getCityName())
            .setFederatedUnityAcronym(movement.getAddress().getFederatedUnityAcronym())
            .setCauses(movement.getCauseThatSupport().stream().map(Cause::getDescription).collect(toList()))
            .setNeeds(needMapper.map(movement.getNeeds()))
            .setAvailable(true) // TODO: from feed query
            .setDistance(1); // TODO: from feed query
    }
}
