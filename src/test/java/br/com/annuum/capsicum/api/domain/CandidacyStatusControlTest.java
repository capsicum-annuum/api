package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.exceptions.StatusTransitionExcepetion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(value = Parameterized.class)
class CandidacyStatusControlTest {

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"REJECTED", "APPROVED", "DECLINED"})
    void shouldSetAvailableNextOptionsOfCandidacyStatusCandidate(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        assertDoesNotThrow(() -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"ABSENT", "PRESENT"})
    void shouldReturnExceptionWhenSettingNotAvailableNextOptionsOfCandidacyStatusCandidate(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        assertThrows(StatusTransitionExcepetion.class, () -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "APPROVED"})
    void shouldSetAvailableNextOptionsOfCandidacyStatusRejected(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.REJECTED);
        assertDoesNotThrow(() -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"DECLINED", "ABSENT", "PRESENT"})
    void shouldReturnExceptionWhenSettingNotAvailableNextOptionsOfCandidacyStatusRejected(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.REJECTED);
        assertThrows(StatusTransitionExcepetion.class, () -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "DECLINED", "ABSENT", "PRESENT"})
    void shouldSetAvailableNextOptionsOfCandidacyStatusApproved(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.APPROVED);
        assertDoesNotThrow(() -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "APPROVED"})
    void shouldSetAvailableNextOptionsOfCandidacyStatusDeclined(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.DECLINED);
        assertDoesNotThrow(() -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"REJECTED", "ABSENT", "PRESENT"})
    void shouldReturnExceptionWhenSettingNotAvailableNextOptionsOfCandidacyStatusDeclined(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.DECLINED);
        assertThrows(StatusTransitionExcepetion.class, () -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"PRESENT", "APPROVED"})
    void shouldSetAvailableNextOptionsOfCandidacyStatusAbsent(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.APPROVED);
        candidacyStatusControl.setStatusEnum(CandidacyStatus.ABSENT);
        assertDoesNotThrow(() -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "DECLINED"})
    void shouldReturnExceptionWhenSettingNotAvailableNextOptionsOfCandidacyStatusAbsent(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.APPROVED);
        candidacyStatusControl.setStatusEnum(CandidacyStatus.ABSENT);
        assertThrows(StatusTransitionExcepetion.class, () -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"ABSENT", "APPROVED"})
    void shouldSetAvailableNextOptionsOfCandidacyStatusPresent(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.APPROVED);
        candidacyStatusControl.setStatusEnum(CandidacyStatus.PRESENT);
        assertDoesNotThrow(() -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "DECLINED"})
    void shouldReturnExceptionWhenSettingNotAvailableNextOptionsOfCandidacyStatusPresent(CandidacyStatus candidacyStatus) {
        CandidacyStatusControl candidacyStatusControl = new CandidacyStatusControl();
        candidacyStatusControl.setStatusEnum(CandidacyStatus.APPROVED);
        candidacyStatusControl.setStatusEnum(CandidacyStatus.PRESENT);
        assertThrows(StatusTransitionExcepetion.class, () -> candidacyStatusControl.setStatusEnum(candidacyStatus));
    }

}
