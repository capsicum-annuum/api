package br.com.annuum.capsicum.api;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.annuum.capsicum.api.domain.CausesDomain;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import br.com.annuum.capsicum.api.service.CauseService;

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
        CausesDomain pets = new CausesDomain().setId(1L).setDescription("Pets");
        CausesDomain kids = new CausesDomain().setId(2L).setDescription("Kids");

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
        CausesDomain pets = new CausesDomain().setId(3L).setDescription("pets");

        service.save(pets);

        Assertions.assertEquals(2, repository.findAll().size());
    }

    @Test
    public void shouldSaveWhenDescriptionIsNotDuplicated() {
        CausesDomain velhos = new CausesDomain().setId(3L).setDescription("Velhos");

        service.save(velhos);

        Assertions.assertEquals(3, repository.findAll().size());
    }
}