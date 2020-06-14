package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.domain.FederatedUnity;
import br.com.annuum.capsicum.api.repository.AddressRepository;
import com.vividsolutions.jts.geom.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveAddressServiceTest {

    @InjectMocks
    private SaveAddressService saveAddressService;

    @Mock
    private FindCityByIdService findCityByIdService;

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
            .setFederatedUnity(Mockito.mock(FederatedUnity.class));
        final Point geolocation = Mockito.mock(Point.class);
        final Address expectedAddress = new Address()
            .setId(1L)
            .setCity(city)
            .setStreetName("someStreet")
            .setLatitude(1D)
            .setLongitude(1D)
            .setGeolocation(geolocation);
        final AddressRequest addressRequest = Mockito.mock(AddressRequest.class);

        when(findCityByIdService.find(addressRequest.getIdCity()))
            .thenReturn(city);
        when(modelMapper.map(addressRequest, Address.class))
            .thenReturn(expectedAddress);
        when(pointFactory.createPointFromCoordinates(addressRequest.getLatitude(), addressRequest.getLongitude()))
            .thenReturn(geolocation);
        when(addressRepository.save(expectedAddress))
            .thenReturn(expectedAddress);

        // Act
        final Address returnedAddress = saveAddressService.save(addressRequest);

        // Assert
        assertEquals(expectedAddress, returnedAddress);
        verify(addressRepository, times(1)).save(expectedAddress);
    }

    @Test
    public void mustSaveAndReturnPersistedAddressWithoutCity_withSuccess() {
        // Arrange
        final Point geolocation = Mockito.mock(Point.class);
        final Address expectedAddress = new Address()
            .setId(1L)
            .setStreetName("someStreet")
            .setLatitude(1D)
            .setLongitude(1D)
            .setGeolocation(geolocation);
        final AddressRequest addressRequest = Mockito.mock(AddressRequest.class);

        verifyNoMoreInteractions(findCityByIdService);
        when(modelMapper.map(addressRequest, Address.class))
            .thenReturn(expectedAddress);
        when(pointFactory.createPointFromCoordinates(addressRequest.getLatitude(), addressRequest.getLongitude()))
            .thenReturn(geolocation);
        when(addressRepository.save(expectedAddress))
            .thenReturn(expectedAddress);

        // Act
        final Address returnedAddress = saveAddressService.save(addressRequest);

        // Assert
        assertEquals(expectedAddress, returnedAddress);
        verify(addressRepository, times(1)).save(expectedAddress);

    }
}
