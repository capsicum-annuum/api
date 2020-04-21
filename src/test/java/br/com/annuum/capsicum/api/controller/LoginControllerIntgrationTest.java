package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoginControllerIntgrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldLoginSuccessfullyWithCorrectUsernameAndPassword() throws Exception {

        final String username = "voluntario@ajuda.ai";
        final String password = "123456";

        final LoginRequest request = new LoginRequest().setUsername(username).setPassword(password);
        final String json = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"token\":\"Bearer ")));
    }


    @Test
    public void shouldFailLoginWithCorrectUsernameAndWrongPassword() throws Exception {
        final String username = "voluntario@ajuda.ai";
        final String password = randomAlphanumeric(8);

        final LoginRequest request = new LoginRequest().setUsername(username).setPassword(password);
        final String json = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(emptyString()));
    }

    @Test
    public void shouldFailLoginWithWrongUsername() throws Exception {
        final String username = randomAlphanumeric(8);
        final String password = "123456";

        final LoginRequest request = new LoginRequest().setUsername(username).setPassword(password);
        final String json = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(emptyString()));
    }

}