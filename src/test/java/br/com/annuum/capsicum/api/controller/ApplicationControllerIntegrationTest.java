package br.com.annuum.capsicum.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApplicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnGreetingsToGuestFromService() throws Exception {
        this.mockMvc.perform(get("/guest"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Guest | GUEST")));
    }

    @Test
    @WithUserDetails("voluntario@ajuda.ai")
    public void greetingShouldReturnGreetingsToVolunteerFromService() throws Exception {
        this.mockMvc.perform(get("/volunteer"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Volunteer | VOLUNTEER")));
    }

    @Test
    @WithUserDetails("grupo@ajuda.ai")
    public void greetingShouldReturnGreetingsToGroupFromService() throws Exception {
        this.mockMvc.perform(get("/group"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Group | GROUP")));
    }

    @Test
    @WithUserDetails("ong@ajuda.ai")
    public void greetingShouldReturnGreetingsToOrganizationFromService() throws Exception {
        this.mockMvc.perform(get("/organization"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Organization | ORGANIZATION")));
    }

    @Test
    @WithUserDetails("voluntario@ajuda.ai")
    public void greetingShouldReturnGreetingsToVolunteerFromServiceForAllUsersEndpoint() throws Exception {
        this.mockMvc.perform(get("/user"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Volunteer | VOLUNTEER")));
    }

    @Test
    @WithUserDetails("grupo@ajuda.ai")
    public void greetingShouldReturnGreetingsToGroupFromServiceForAllUsersEndpoint() throws Exception {
        this.mockMvc.perform(get("/user"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Group | GROUP")));
    }

    @Test
    @WithUserDetails("ong@ajuda.ai")
    public void greetingShouldReturnGreetingsToOrganizationFromServiceForAllUsersEndpoint() throws Exception {
        this.mockMvc.perform(get("/user"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Organization | ORGANIZATION")));
    }

}
