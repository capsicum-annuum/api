package br.com.annuum.capsicum.api.validator;

import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SaveEvaluationValidatorTest {

    @InjectMocks
    private SaveEvaluationValidator saveEvaluationValidator;

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"APPROVED", "DECLINED", "REJECTED"})
    public void mustReturnExceptionWhenUserEvaluatedHasCandidacyStatusApprovedOrDeclinedOrRejected(CandidacyStatus candidacyStatus) {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        candidacy.getCandidacyStatusControl().setStatusEnum(candidacyStatus);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        assertThrows(AccessControlException.class, () -> saveEvaluationValidator.validate(movement, candidacy));
    }

    @Test
    public void mustReturnExceptionWhenUserEvaluatedHasCandidacyStatusCandidate() {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        assertThrows(AccessControlException.class, () -> saveEvaluationValidator.validate(movement, candidacy));
    }

    @Test
    public void mustReturnExceptionWhenUserEvaluatedHasCandidacyStatusAbsent() {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.ABSENT);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        assertThrows(AccessControlException.class, () -> saveEvaluationValidator.validate(movement, candidacy));
    }

    @ParameterizedTest
    @EnumSource(value = MovementStatus.class, names = {"ACTIVE", "CANCELLED"})
    public void mustReturnExceptionWhenEvaluatingUserVolunteerMovementStatusDifferentThanConclude(MovementStatus movementStatus) {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(movementStatus);

        assertThrows(AccessControlException.class, () -> saveEvaluationValidator.validate(movement, candidacy));
    }


}
