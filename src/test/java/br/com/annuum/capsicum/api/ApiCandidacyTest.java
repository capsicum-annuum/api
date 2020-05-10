package br.com.annuum.capsicum.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApiCandidacyTest {

    @Test
    void shouldInitialize() {
        Assertions.assertTrue(true, "Should be initialized");
    }

}
