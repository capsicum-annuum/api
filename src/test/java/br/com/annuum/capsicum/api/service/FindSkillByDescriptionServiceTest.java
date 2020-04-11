package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.SkillRepository;
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
class FindSkillByDescriptionServiceTest {

    @InjectMocks
    FindSkillByDescriptionService findSkillByDescriptionService;

    @Mock
    private SkillRepository skillRepository;

    @Test
    public void mustReturnSkillThatDescriptionGivenMatchWithPersistedSkills_withSuccess() {
        // Arrange
        final String skillDescription = "someCause";
        final Skill expectedSkill = new Skill()
                .setId(1L)
                .setDescription("someSkill");

        Mockito.when(skillRepository.findByDescription(skillDescription))
                .thenReturn(Optional.of(expectedSkill));

        // Act
        Skill returnedSkill = findSkillByDescriptionService.find(skillDescription);

        // Assert
        assertEquals(expectedSkill, returnedSkill);
    }

    @Test
    public void mustThrowRegisterNotFoundExceptionWhenDescriptionGivenNotMatchWithPersistedSkills_withSuccess() {
        // Arrange
        final String skillDescription = "someskill";
        Mockito.when(skillRepository.findByDescription(skillDescription))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RegisterNotFoundException.class, () -> findSkillByDescriptionService.find(skillDescription));
    }
}