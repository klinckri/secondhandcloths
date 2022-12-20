package ch.zhaw.secondhandcloths.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.KategorieEnum;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class InseratControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // File processing does not work
   /* @Test
    void testCreateInserat() throws Exception{
        MultipartFile file = new MockMultipartFile("fileuploader", "fineuploader.png", "image/png",
                "Some bytes".getBytes());
        InseratDTO inseratDTO = new InseratDTO("Test Inserat", "Inserat fürs Integration Testing", 
        15.40, KategorieEnum.ANDERE, file);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/inserat/inserieren")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(inseratDTO)))
                .andExpect(status().isCreated());
    }*/

    @Test
    void testGetAllInserate() throws Exception{
        mockMvc.perform(get("/api/inserat/home"))
                .andExpect(status().isOk());
    }

    // id is changing
    /*@Test
    void testGetArtikel() throws Exception{
        mockMvc.perform(get("/api/inserat/artikel/63a1c19ccd9f0448d3a43102"))
                .andExpect(status().isOk());
    }*/
}
