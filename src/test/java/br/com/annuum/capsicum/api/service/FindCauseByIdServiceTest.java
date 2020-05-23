package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FindCauseByIdServiceTest {

    @InjectMocks
    FindCauseByIdService findCauseByIdService;

    @Mock
    private CauseRepository causeRepository;

    @Test
    public void mustReturnCauseThatDescriptionGivenMatchWithPersistedCauses_withSuccess() {
        // Arrange
        final Long causeId = 1L;
        final Cause expectedCause = new Cause()
            .setId(1L)
            .setDescription("someCause");

        Mockito.when(causeRepository.findById(causeId))
            .thenReturn(Optional.of(expectedCause));

        // Act
        final Cause returnedCause = findCauseByIdService.find(causeId);

        // Assert
        assertEquals(expectedCause, returnedCause);
    }

    @Test
    public void mustThrowRegisterNotFoundExceptionWhenDescriptionGivenNotMatchWithPersistedCauses_withSuccess() {
        // Arrange
        final Long causeId = 1L;
        Mockito.when(causeRepository.findById(causeId))
            .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RegisterNotFoundException.class, () -> findCauseByIdService.find(causeId));
    }

}
