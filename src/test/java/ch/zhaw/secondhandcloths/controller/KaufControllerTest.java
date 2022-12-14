package ch.zhaw.secondhandcloths.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import ch.zhaw.secondhandcloths.model.KaufDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class KaufControllerTest {

    @Autowired
    private MockMvc mockMvc; 
/*
    @Test
    void testArtikelKaufen() throws Exception{
        KaufDTO kaufDTO = new KaufDTO("637c6673498da731157c71c8", "CH43 9876 5432 12", "02/25", 234, new ArrayList<>());
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/kauf")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(kaufDTO)))
                .andExpect(status().isOk());
    }
     */
}
