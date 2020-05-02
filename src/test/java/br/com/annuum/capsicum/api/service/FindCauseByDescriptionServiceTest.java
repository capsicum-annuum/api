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
class FindCauseByDescriptionServiceTest {

    @InjectMocks
    FindCauseByDescriptionService findCauseByDescriptionService;

    @Mock
    private CauseRepository causeRepository;

    @Test
    public void mustReturnCauseThatDescriptionGivenMatchWithPersistedCauses_withSuccess() {
        // Arrange
        final String causeDescription = "someCause";
        final Cause expectedCause = new Cause()
            .setId(1L)
            .setDescription("someCause");

        Mockito.when(causeRepository.findByDescription(causeDescription))
            .thenReturn(Optional.of(expectedCause));

        // Act
        final Cause returnedCause = findCauseByDescriptionService.find(causeDescription);

        // Assert
        assertEquals(expectedCause, returnedCause);
    }

    @Test
    public void mustThrowRegisterNotFoundExceptionWhenDescriptionGivenNotMatchWithPersistedCauses_withSuccess() {
        // Arrange
        final String causeDescription = "someCause";
        Mockito.when(causeRepository.findByDescription(causeDescription))
            .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RegisterNotFoundException.class, () -> findCauseByDescriptionService.find(causeDescription));
    }

}
