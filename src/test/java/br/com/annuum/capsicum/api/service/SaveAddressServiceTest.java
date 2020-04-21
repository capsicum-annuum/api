package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.domain.SpatialLocation;
import br.com.annuum.capsicum.api.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.com.annuum.capsicum.api.domain.enums.State.RS;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveAddressServiceTest {

    @InjectMocks
    private SaveAddressService saveAddressService;

    @Mock
    private FindOrCreateNewCityService findOrCreateNewCityService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PointFactory pointFactory;

    @Test
    public void mustSaveAndReturnPersistedAddress_withSuccess() {
        // Arrange
        final City city = new City()
                .setId(1L)
                .setName("someCityName")
                .setState(RS);
        final SpatialLocation spatialLocation = new SpatialLocation()
                .setLatitude(1D)
                .setLongitude(1D);
        final Address expectedAddress = new Address()
                .setId(1L)
                .setCity(city)
                .setStreetName("someStreet")
                .setSpatialLocation(spatialLocation);
        final AddressRequest addressRequest = Mockito.mock(AddressRequest.class);

        Mockito.when(findOrCreateNewCityService.findOrCreateNewCity(addressRequest.getCityRequest()))
                .thenReturn(city);
        Mockito.when(modelMapper.map(addressRequest, Address.class))
                .thenReturn(expectedAddress);
        Mockito.when(modelMapper.map(addressRequest.getSpatialLocationRequest(), SpatialLocation.class))
                .thenReturn(spatialLocation);
        Mockito.when(addressRepository.save(expectedAddress))
                .thenReturn(expectedAddress);

        // Act
        final Address returnedAddress = saveAddressService.saveAddress(addressRequest);

        // Assert
        Assertions.assertEquals(expectedAddress, returnedAddress);
        Mockito.verify(addressRepository, times(1)).save(expectedAddress);
    }


}