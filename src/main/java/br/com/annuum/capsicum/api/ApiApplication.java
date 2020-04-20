package br.com.annuum.capsicum.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableJpaAuditing
@SpringBootApplication
public class ApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
