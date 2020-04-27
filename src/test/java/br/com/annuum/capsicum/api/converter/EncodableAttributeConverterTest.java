package br.com.annuum.capsicum.api.converter;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Encodable;
import br.com.annuum.capsicum.api.domain.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith({MockitoExtension.class})
class EncodableAttributeConverterTest {

    private EncodableAttributeConverter encodableAttributeConverter = new EncodableAttributeConverter();

    @Test
    public void mustEncondeSkillAttributeToBinaryCode() {
        // Arrange
        final Skill skill1 = new Skill()
            .setId(1L)
            .setName("skill1")
            .setBinaryIdentifier(2);
        final Skill skill2 = new Skill()
            .setId(2L)
            .setName("skill2")
            .setBinaryIdentifier(4);
        final String expectedBinaryCode = Integer.toBinaryString(skill1.getBinaryIdentifier()) + Integer.toBinaryString(skill2.getBinaryIdentifier());
        final List<Encodable> encodables = Arrays.asList(skill1, skill2);

        // Act
        final String returnedBinaryCode = encodableAttributeConverter.convertToBinaryCode(encodables);

        // Assert
        Assertions.assertEquals(expectedBinaryCode, returnedBinaryCode);
    }

    @Test
    public void mustEncondeCauseAttributeToBinaryCode() {
        // Arrange
        final Cause cause1 = new Cause()
            .setId(1L)
            .setDescription("cause1")
            .setBinaryIdentifier(2);
        final Cause cause2 = new Cause()
            .setId(2L)
            .setDescription("cause2")
            .setBinaryIdentifier(4);
        final String expectedBinaryCode = Integer.toBinaryString(cause1.getBinaryIdentifier()) + Integer.toBinaryString(cause2.getBinaryIdentifier());
        final List<Encodable> encodables = Arrays.asList(cause1, cause2);

        // Act
        final String returnedBinaryCode = encodableAttributeConverter.convertToBinaryCode(encodables);

        // Assert
        Assertions.assertEquals(expectedBinaryCode, returnedBinaryCode);
    }

}
