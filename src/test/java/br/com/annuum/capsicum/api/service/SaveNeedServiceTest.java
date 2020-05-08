package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AvailabilityRequest;
import br.com.annuum.capsicum.api.controller.request.DayShiftAvailabilityRequest;
import br.com.annuum.capsicum.api.controller.request.NeedRequest;
import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.DayShiftAvailability;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.enums.DayShift;
import br.com.annuum.capsicum.api.repository.NeedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.DayOfWeek;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveNeedServiceTest {

    @InjectMocks
    private SaveNeedService saveNeedService;

    @Mock
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Mock
    private NeedRepository needRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveNeed_withSuccess() {
        //Arrange
        final Skill skill = new Skill()
            .setId(1L)
            .setDescription("someSkill")
            .setBinaryIdentifier(2);

        final DayShiftAvailabilityRequest dayShiftAvailabilityRequest = new DayShiftAvailabilityRequest()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final DayShiftAvailability dayShiftAvailability = new DayShiftAvailability()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final AvailabilityRequest availabilityRequest = new AvailabilityRequest()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailabilityRequest));

        final Availability availability = new Availability()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailability));

        final Need expectedNeed = new Need()
            .setId(1L)
            .setSkill(skill)
            .setDescription("someDescription")
            .setQuantity(1)
            .setAvailability(availability)
            .setIsActive(true);

        final NeedRequest needRequest = new NeedRequest()
            .setSkill("someSkill")
            .setDescription("someDescription")
            .setQuantity(1)
            .setAvailabilityRequest(availabilityRequest)
            .setIsActive(true);

        Mockito.when(findSkillByDescriptionService.find(skill.getDescription()))
            .thenReturn(skill);
        Mockito.when(modelMapper.map(dayShiftAvailabilityRequest, DayShiftAvailability.class))
            .thenReturn(dayShiftAvailability);
        Mockito.when(modelMapper.map(needRequest, Need.class))
            .thenReturn(expectedNeed);
        Mockito.when(needRepository.save(expectedNeed))
            .thenReturn(expectedNeed);

        //Act
        final Need returnedNeed = saveNeedService.save(needRequest);

        //Assert
        assertEquals(expectedNeed, returnedNeed);
        Mockito.verify(needRepository, times(1)).save(expectedNeed);
    }
}
