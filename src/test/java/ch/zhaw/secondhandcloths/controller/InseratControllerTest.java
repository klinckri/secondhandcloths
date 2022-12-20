package ch.zhaw.secondhandcloths.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class InseratControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InseratRepository inseratRepository;

    // File processing does not work
    /*
     * @Test
     * void testCreateInserat() throws Exception{
     * MultipartFile file = new MockMultipartFile("fileuploader",
     * "fineuploader.png", "image/png",
     * "Some bytes".getBytes());
     * InseratDTO inseratDTO = new InseratDTO("Test Inserat",
     * "Inserat fürs Integration Testing",
     * 15.40, KategorieEnum.ANDERE, file);
     * ObjectMapper mapper = new ObjectMapper();
     * mockMvc.perform(post("/api/inserat/inserieren")
     * .contentType("application/json")
     * .content(mapper.writeValueAsBytes(inseratDTO)))
     * .andExpect(status().isCreated());
     * }
     */

    @Test
    void testGetAllInserate() throws Exception {
        mockMvc.perform(get("/api/inserat/home"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetArtikel() throws Exception {
        Inserat inserat = inseratRepository
                .save(new Inserat("Titel", "Beschreibung", 23.90, KategorieEnum.ANDERE, new Person("test@gmail.com"), "file.png"));
        mockMvc.perform(get("/api/inserat/artikel/" + inserat.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllInserateFromPerson() throws Exception {
        mockMvc.perform(
                get("/api/inserat/person")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbImFkbWluIl0sIm5pY2tuYW1lIjoia2xpbmNrcmkiLCJuYW1lIjoia2xpbmNrcmlAc3R1ZGVudHMuemhhdy5jaCIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9hYjI2NGU5OGY3NzQ3MjZlNTNkYzQ4ZGJlODYxNjFkNT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmtsLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTEyLTIwVDE1OjA0OjAwLjM0MVoiLCJlbWFpbCI6ImtsaW5ja3JpQHN0dWRlbnRzLnpoYXcuY2giLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9kZXYtM2JjbDB1c3JpNWtvM2dnMC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjM4NWM5NzU2N2FkM2UwOTAzMGNkNThmIiwiYXVkIjoiWm1UblV3S1h6S0sxY3lTMnZZd3dMdDd2NEVuRDRvWEQiLCJpYXQiOjE2NzE1NDg2NDEsImV4cCI6MTY3MTU4NDY0MSwic2lkIjoiNk9XMGxfRTNndHZkVnRlRkZ6dGxCUzBHbmJwRWY2U3QiLCJub25jZSI6IlR6SmpaREZzTUdGaU1uRTNjSEZXWjNjMk4ybzVSMGRyTlU5dFRsVkNUbjVPV1ZKWGRVRmFRa2RGU2c9PSJ9.SXLtTire3Nm_Bm_dHseWXyk_B_BjAeH1mt66K91QRnnFRiP8BXO6fswIhB3-A9d-gIXNz8g6Eoi3xvL2khJ4L9_KqRv_NeUrPynGRhKj82LApo_46DsJ5oXxj4lLR9e9HD-ZPS3Wj2siqdHgrORopJrdEHEeeYVoxydao6LacO-ga-kgzJnUAWvqPJGC8_MFN2u1d_U4bSVLtQJQ0Z-igd3gJmY7vlxfGItpKOQ8EKPTOCT5J-x0DDLTHP_gYVarrwL1xgpLt7jFZA4Ig_w0xccrqZ7faUAhT6AFCtFDN6LR6Msht0FD0jDc1XbK2UKb5pnHT4BFVzZbpX67Qkd4Nw"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteInserat() throws Exception {
        Inserat inserat = inseratRepository
                .save(new Inserat("Titel", "Beschreibung", 23.90, KategorieEnum.ANDERE, new Person("test@gmail.com"), "file.png"));
        mockMvc.perform(
                delete("/api/inserat/delete/" + inserat.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbImFkbWluIl0sIm5pY2tuYW1lIjoia2xpbmNrcmkiLCJuYW1lIjoia2xpbmNrcmlAc3R1ZGVudHMuemhhdy5jaCIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9hYjI2NGU5OGY3NzQ3MjZlNTNkYzQ4ZGJlODYxNjFkNT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmtsLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTEyLTIwVDE1OjA0OjAwLjM0MVoiLCJlbWFpbCI6ImtsaW5ja3JpQHN0dWRlbnRzLnpoYXcuY2giLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9kZXYtM2JjbDB1c3JpNWtvM2dnMC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjM4NWM5NzU2N2FkM2UwOTAzMGNkNThmIiwiYXVkIjoiWm1UblV3S1h6S0sxY3lTMnZZd3dMdDd2NEVuRDRvWEQiLCJpYXQiOjE2NzE1NDg2NDEsImV4cCI6MTY3MTU4NDY0MSwic2lkIjoiNk9XMGxfRTNndHZkVnRlRkZ6dGxCUzBHbmJwRWY2U3QiLCJub25jZSI6IlR6SmpaREZzTUdGaU1uRTNjSEZXWjNjMk4ybzVSMGRyTlU5dFRsVkNUbjVPV1ZKWGRVRmFRa2RGU2c9PSJ9.SXLtTire3Nm_Bm_dHseWXyk_B_BjAeH1mt66K91QRnnFRiP8BXO6fswIhB3-A9d-gIXNz8g6Eoi3xvL2khJ4L9_KqRv_NeUrPynGRhKj82LApo_46DsJ5oXxj4lLR9e9HD-ZPS3Wj2siqdHgrORopJrdEHEeeYVoxydao6LacO-ga-kgzJnUAWvqPJGC8_MFN2u1d_U4bSVLtQJQ0Z-igd3gJmY7vlxfGItpKOQ8EKPTOCT5J-x0DDLTHP_gYVarrwL1xgpLt7jFZA4Ig_w0xccrqZ7faUAhT6AFCtFDN6LR6Msht0FD0jDc1XbK2UKb5pnHT4BFVzZbpX67Qkd4Nw"))
                .andExpect(status().isOk());

            Inserat inserat2 = inseratRepository
                .save(new Inserat("Titel", "Beschreibung", 23.90, KategorieEnum.ANDERE, new Person("test@gmail.com"), "file.png"));
        mockMvc.perform(
                delete("/api/inserat/delete/" + inserat2.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbXSwibmlja25hbWUiOiJjb2NvLmphbWJvIiwibmFtZSI6ImNvY28uamFtYm9AYWphamUuY2giLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvYWZhMzI3ZDU1MTY1NWQ1NzBlODY1MzFkMzRkOGNlNWE_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZjby5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMi0xMi0yMFQxNTo1ODo1MS4yNjRaIiwiZW1haWwiOiJjb2NvLmphbWJvQGFqYWplLmNoIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJpc3MiOiJodHRwczovL2Rldi0zYmNsMHVzcmk1a28zZ2cwLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2M2ExYmEwZDI2NjgyMjY0Y2FkNDgzYjIiLCJhdWQiOiJabVRuVXdLWHpLSzFjeVMydll3d0x0N3Y0RW5ENG9YRCIsImlhdCI6MTY3MTU1MTkzMiwiZXhwIjoxNjcxNTg3OTMyLCJzaWQiOiJuRU1mX2NwaFRXelJ1TWxpUzdNbWY4TTJCLWRoa1dfUCIsIm5vbmNlIjoiUkVsWGEyaG9WMFJaVjBaWFVFUlRSa1U1ZUVOUFoyOURWbFpDTTJkMVpEbFRkRmN3ZWxCSmNYVTRadz09In0.ea4FdfSmEHK_eexhAl6n8NwAdbdp8kojOwMp3AyjITsbwqiWStjlMPKSrthLMinf6iV_8t3ONizbhClk6X_jLLu6UzBSZ6cX_nUIm62EiEgiOFfFK4JcWjonjMa3Gqf34376BV-9JRr5W9PgDpacXkM94QZ-Np5Qzu7g1i6UJgisaId9n9YUro4qAdgohCCfFZmzMZfuebWI0OD1Y-z7dkFVCXH8hObs6mS6Q87WTQU-JRWs1FJd0ZnWllmWZVAxO3Vk9yWi1lE-W0qWsiztnYUueWjM1LHlCWNlQIRpjt8OPdjkChuk9WxnshDbDmrVn0cJYR8T-rpVJ7UModE0dg"))
                .andExpect(status().isForbidden());
    }
}
