package ch.zhaw.secondhandcloths.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.KategorieEnum;

@SpringBootTest
@AutoConfigureMockMvc
public class InseratControllerTest {

    @Autowired
    private MockMvc mockMvc;
/*
    @Test
    void testCreateInserat() throws Exception{
        InseratDTO inseratDTO = new InseratDTO("Test Inserat", "Inserat fürs Integration Testing", 
        15.40, "CH41 5678 9876 23", KategorieEnum.ANDERE, null);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/inserat/inserieren")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(inseratDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllByKategorie() throws Exception{
        mockMvc.perform(get("/api/inserat/home/KLEIDER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titel", is("UNISEX schwarzes T-Shirt von Giorgio Armani")))
                .andExpect(jsonPath("$[0].beschreibung", is("UNISEX, schwarz, Baumwolle, Giorgio Armani...")))
                .andExpect(jsonPath("$[0].preis", is(19.5)))
                .andExpect(jsonPath("$[0].iban", is("CH41 1234 5678 90")))
                .andExpect(jsonPath("$[0].kategorie", is(KategorieEnum.KLEIDER.name())))
                .andExpect(jsonPath("$[0].personId.id", is("637c6673498da731157c71c8")));
    }

    @Test
    void testGetAllInserate() throws Exception{
        mockMvc.perform(get("/api/inserat/home"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].titel", is("UNISEX schwarzes T-Shirt von Giorgio Armani")))
                .andExpect(jsonPath("$[2].beschreibung", is("UNISEX, schwarz, Baumwolle, Giorgio Armani...")))
                .andExpect(jsonPath("$[2].preis", is(19.5)))
                .andExpect(jsonPath("$[2].iban", is("CH41 1234 5678 90")))
                .andExpect(jsonPath("$[2].kategorie", is(KategorieEnum.KLEIDER.name())))
                .andExpect(jsonPath("$[2].personId.id", is("637c6673498da731157c71c8")));
    }

    @Test
    void testGetArtikel() throws Exception{
        mockMvc.perform(get("/api/inserat/artikel/637ce92589c01f2b9f456934"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titel", is("UNISEX schwarzes T-Shirt von Giorgio Armani")))
                .andExpect(jsonPath("$.beschreibung", is("UNISEX, schwarz, Baumwolle, Giorgio Armani...")))
                .andExpect(jsonPath("$.preis", is(19.5)))
                .andExpect(jsonPath("$.iban", is("CH41 1234 5678 90")))
                .andExpect(jsonPath("$.kategorie", is(KategorieEnum.KLEIDER.name())))
                .andExpect(jsonPath("$.personId.id", is("637c6673498da731157c71c8")));
    }
 */
}
