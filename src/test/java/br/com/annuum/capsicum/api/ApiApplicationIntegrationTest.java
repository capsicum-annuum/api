package br.com.annuum.capsicum.api;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.annuum.capsicum.api.domain.CausesDomain;
import br.com.annuum.capsicum.api.repository.CauseRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ApiApplicationIntegrationTest {

    @Resource
    private CauseRepository causeRepository;

    @BeforeEach
    public void setup() {
        CausesDomain pets = new CausesDomain().setId(1L).setDescription("Pets");
        CausesDomain kids = new CausesDomain().setId(2L).setDescription("Kids");

        causeRepository.save(pets);
        causeRepository.save(kids);
    }

    @Test
    public void test() {

        List<CausesDomain> causes = causeRepository.findAll();
        Assertions.assertEquals(2, causes.size());

    }

}
