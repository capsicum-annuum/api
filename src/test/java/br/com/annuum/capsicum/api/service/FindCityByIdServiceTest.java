package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.domain.State;
import br.com.annuum.capsicum.api.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.annuum.capsicum.api.domain.enums.State.RS;

@ExtendWith(MockitoExtension.class)
class FindCityByIdServiceTest {

    @InjectMocks
    private FindCityByIdService findCityByIdService;

    @Mock
    private CityRepository cityRepository;

    @Test
    public void mustFindAndReturnCity_withSuccess() {
        // Arrange
        final Long idCity = 1L;

        final City expectedCity = new City()
            .setId(1L)
            .setName("someName")
            .setState(Mockito.mock(State.class));

        Mockito.when(cityRepository.findById(idCity))
            .thenReturn(Optional.of(expectedCity));

        // Act
        final City returnedCity = findCityByIdService.find(idCity);

        // Assert
        Assertions.assertEquals(expectedCity, returnedCity);
    }
}
