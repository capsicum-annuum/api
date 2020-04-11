package br.com.annuum.capsicum.api;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import br.com.annuum.capsicum.api.service.CauseService;
import javax.annotation.Resource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CauseServiceIntegrationTest {

    @Resource
    private CauseService service;

    @Resource
    private CauseRepository repository;

    @BeforeAll
    public static void setupAll() {
        System.out.println("SETUP ALL");
    }

    @AfterAll
    public static void cleanupAll() {
        System.out.println("CLEANUP ALL");

    }

    @BeforeEach
    public void setup() {
        System.out.println("SETUP EACH");
        final Cause pets = new Cause().setDescription("Pets");
        final Cause kids = new Cause().setDescription("Kids");

        repository.save(pets);
        repository.save(kids);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("CLEANUP EACH");
        repository.deleteAll();
    }

    @Test
    public void shouldNotSaveWhenDescriptionIsDuplicated() {
        final Cause pets = new Cause().setDescription("pets");

        service.save(pets);

        Assertions.assertEquals(2, repository.findAll().size());
    }

    @Test
    public void shouldSaveWhenDescriptionIsNotDuplicated() {
        final Cause velhos = new Cause().setDescription("Velhos");

        service.save(velhos);

        Assertions.assertEquals(3, repository.findAll().size());
    }
}