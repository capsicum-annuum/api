package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.controller.request.NeedRequest;
import br.com.annuum.capsicum.api.controller.response.MovementResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveMovementServiceTest {

    @InjectMocks
    private SaveMovementService saveMovementService;

    @Mock
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Mock
    private FindUserByIdService findUserByIdService;

    @Mock
    private MovementRepository movementRepository;

    @Mock
    private SaveAddressService saveAddressService;

    @Mock
    private SaveNeedService saveNeedService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveAndReturnNewMovement_withSuccess() {
        // Arrange
        final Address address = Mockito.mock(Address.class);

        final UserGroup userGroup = Mockito.mock(UserGroup.class);

        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("someCause");

        final List<Cause> causeList = Collections.singletonList(cause);

        final Need need = new Need()
            .setId(1L)
            .setSkill(Mockito.mock(Skill.class))
            .setDescription("someDescription")
            .setQuantity(1)
            .setAvailability(new Availability())
            .setIsActive(true);

        final NeedRequest needRequest = new NeedRequest()
            .setSkill("someSkill")
            .setDescription("someDescription")
            .setQuantity(1)
            .setIsActive(true);

        final List<Need> needs = Collections.singletonList(need);

        final List<NeedRequest> needRequests = Collections.singletonList(needRequest);

        final MovementRequest movementRequest = new MovementRequest()
            .setUserAuthorId(1L)
            .setAddressRequest(Mockito.mock(AddressRequest.class))
            .setNeedsRequest(needRequests)
            .setCauseThatSupport(Collections.singletonList("someCause"))
            .setDateTimeStart(LocalDateTime.of(2021, 10, 10, 10, 10))
            .setDateTimeEnd(LocalDateTime.of(2021, 10, 11, 10, 10))
            .setDescription("someDescription")
            .setTitle("someTitle");

        final Movement expectedMovement = new Movement()
            .setUserAuthor(userGroup)
            .setAddress(address)
            .setCauseThatSupport(causeList)
            .setNeeds(needs)
            .setDateTimeStart(LocalDateTime.of(2021, 10, 10, 10, 10))
            .setDateTimeEnd(LocalDateTime.of(2021, 10, 11, 10, 10))
            .setDescription("someDescription")
            .setTitle("someTitle");

        final MovementResponse expectedMovementResponse = new MovementResponse()
            .setDateTimeStart(LocalDateTime.of(2021, 10, 10, 10, 10))
            .setDateTimeEnd(LocalDateTime.of(2021, 10, 11, 10, 10))
            .setDescription("someDescription")
            .setTitle("someTitle");

        Mockito.when(saveAddressService.saveAddress(movementRequest.getAddressRequest()))
            .thenReturn(address);
        Mockito.when(findUserByIdService.find(movementRequest.getUserAuthorId()))
            .thenReturn(userGroup);
        Mockito.when(saveNeedService.save(needRequest))
            .thenReturn(need);
        Mockito.when(findCauseByDescriptionService.find(cause.getDescription()))
            .thenReturn(cause);
        Mockito.when(movementRepository.save(expectedMovement))
            .thenReturn(expectedMovement);
        Mockito.when(modelMapper.map(expectedMovement, MovementResponse.class))
            .thenReturn(expectedMovementResponse);

        //Act
        final MovementResponse returnedMovementResponse = saveMovementService.save(movementRequest);

        //Assert
        assertEquals(expectedMovementResponse, returnedMovementResponse);
        Mockito.verify(movementRepository, times(1)).save(expectedMovement);

    }
}