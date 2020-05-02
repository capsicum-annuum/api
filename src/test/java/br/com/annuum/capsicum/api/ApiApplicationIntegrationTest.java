package br.com.annuum.capsicum.api;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ApiApplicationIntegrationTest {

    @Resource
    private CauseRepository causeRepository;

    @BeforeEach
    public void setup() {
        final Cause pets = new Cause().setDescription("Pets");
        final Cause kids = new Cause().setDescription("Kids");

        causeRepository.save(pets);
        causeRepository.save(kids);
    }

    @Test
    public void test() {

        final List<Cause> causes = causeRepository.findAll();
        Assertions.assertEquals(2, causes.size());

    }

}
