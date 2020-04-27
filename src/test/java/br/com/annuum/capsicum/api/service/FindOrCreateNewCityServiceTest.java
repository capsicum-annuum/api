package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.CityRequest;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static br.com.annuum.capsicum.api.domain.enums.State.RS;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FindOrCreateNewCityServiceTest {

    @InjectMocks
    FindOrCreateNewCityService findOrCreateNewCityService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustFindAndReturnCityThatWasRegistered_withSuccess() {
        // Arrange
        final CityRequest cityRequest = new CityRequest()
            .setGooglePlaceCityIdentifier("someId")
            .setName("someName")
            .setState(RS);

        final City expectedCity = new City()
            .setId(1L)
            .setGooglePlaceCityIdentifier("someId")
            .setName("someName")
            .setState(RS);

        Mockito.when(cityRepository.findByGooglePlaceCityIdentifier(cityRequest.getGooglePlaceCityIdentifier()))
            .thenReturn(Optional.of(expectedCity));

        // Act
        final City returnedCity = findOrCreateNewCityService.findOrCreateNewCity(cityRequest);

        // Assert
        Assertions.assertEquals(expectedCity, returnedCity);
    }

    @Test
    public void mustCreateAndReturnANewCityWhenItIsNotRegisteredYet_withSuccess() {
        // Arrange
        final CityRequest cityRequest = new CityRequest()
            .setGooglePlaceCityIdentifier("someId")
            .setName("someName")
            .setState(RS);

        final City expectedCity = new City()
            .setId(1L)
            .setGooglePlaceCityIdentifier("someId")
            .setName("someName")
            .setState(RS);

        Mockito.when(cityRepository.findByGooglePlaceCityIdentifier(cityRequest.getGooglePlaceCityIdentifier()))
            .thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(cityRequest, City.class))
            .thenReturn(expectedCity);
        Mockito.when(cityRepository.save(expectedCity))
            .thenReturn(expectedCity);

        // Act
        final City returnedCity = findOrCreateNewCityService.findOrCreateNewCity(cityRequest);

        // Assert
        Assertions.assertEquals(expectedCity, returnedCity);
        Mockito.verify(cityRepository, times(1)).save(returnedCity);
    }

}
