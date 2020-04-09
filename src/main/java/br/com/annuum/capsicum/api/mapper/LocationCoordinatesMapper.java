package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.request.LocationCoordinatesRequest;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.service.FindCityByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationCoordinatesMapper {

    @Autowired
    private FindCityByIdService findCityByIdService;

    public LocationCoordinates map(LocationCoordinatesRequest locationCoordinatesRequest) {

        LocationCoordinates locationCoordinates = new LocationCoordinates();

        locationCoordinates.setLatitude(locationCoordinatesRequest.getLatitude());
        locationCoordinates.setLongitude(locationCoordinatesRequest.getLongitude());

        return locationCoordinates;
    }
}
