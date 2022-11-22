package ch.zhaw.secondhandcloths.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.zhaw.secondhandcloths.model.LoginDTO;
import ch.zhaw.secondhandcloths.model.PersonDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @Test
    void testCreatePerson() throws Exception{
        PersonDTO personDTO = new PersonDTO("Kristina", "Klincov", 
        "kristinaklincov@gmail.com", "Bahnhofstrasse 2", 8001, "Zürich", "alsdjfaf", "alsdjfaf");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/person/registrieren")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(personDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetPersonById() throws Exception {
        mockMvc.perform(get("/api/person/637c6673498da731157c71c8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vorname", is("Kristina")))
                .andExpect(jsonPath("$.nachname", is("Klincov")))
                .andExpect(jsonPath("$.email", is("klinckri@students.zhaw.ch")))
                .andExpect(jsonPath("$.strasse", is("Bahnhofstrasse 100")))
                .andExpect(jsonPath("$.plz", is(8001)))
                .andExpect(jsonPath("$.ort", is("Zürich")))
                .andExpect(jsonPath("$.passwort", is("xhansitldpahdd")));
    }

    @Test
    void testLogin_isOk() throws Exception{
        LoginDTO loginDTO = new LoginDTO("klinckri@students.zhaw.ch", "xhansitldpahdd");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/person/login")
        .contentType("application/json")
        .content(mapper.writeValueAsBytes(loginDTO)))
        .andExpect(status().isOk());
    }

    @Test
    void testLogin_isForbidden() throws Exception{
        LoginDTO loginDTO = new LoginDTO("klinckri@students.zhaw.ch", "abc");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/person/login")
        .contentType("application/json")
        .content(mapper.writeValueAsBytes(loginDTO)))
        .andExpect(status().isForbidden());
    }

    @Test
    void testLogin_isBadRequest() throws Exception{
        LoginDTO loginDTO = new LoginDTO("kristina@students.zhaw.ch", "abc");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/person/login")
        .contentType("application/json")
        .content(mapper.writeValueAsBytes(loginDTO)))
        .andExpect(status().isBadRequest());
    }
}
