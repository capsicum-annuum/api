package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.response.MovementSimpleResponse;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Movement;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class MovementSimpleResponseMapper implements Mapper<Movement, MovementSimpleResponse> {

    @Override
    public MovementSimpleResponse map(Movement movement) {
        return new MovementSimpleResponse()
            .setId(movement.getId())
            .setTitle(movement.getTitle())
            .setDescription(movement.getDescription())
            .setAuthor(movement.getUserAuthor().getName())
            .setAuthorImageUrl(movement.getUserAuthor().getProfilePictureUrl())
            .setImageUrl(movement.getPictureUrl())
            .setStart(movement.getDateTimeStart())
            .setEnd(movement.getDateTimeEnd())
            .setCityName(movement.getAddress().getCityName())
            .setFederatedUnityAcronym(movement.getAddress().getFederatedUnityAcronym())
            .setCauses(movement.getCauseThatSupport().stream().map(Cause::getDescription).collect(toList()))
            .setAvailable(true) // TODO: from feed query
            .setDistance(1); // TODO: from feed query
    }
}
