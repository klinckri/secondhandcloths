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
                                + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbImFkbWluIl0sIm5pY2tuYW1lIjoia2xpbmNrcmkiLCJuYW1lIjoia2xpbmNrcmlAc3R1ZGVudHMuemhhdy5jaCIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9hYjI2NGU5OGY3NzQ3MjZlNTNkYzQ4ZGJlODYxNjFkNT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmtsLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTEyLTIyVDA3OjM1OjQ5LjEzNVoiLCJlbWFpbCI6ImtsaW5ja3JpQHN0dWRlbnRzLnpoYXcuY2giLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9kZXYtM2JjbDB1c3JpNWtvM2dnMC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjM4NWM5NzU2N2FkM2UwOTAzMGNkNThmIiwiYXVkIjoiWm1UblV3S1h6S0sxY3lTMnZZd3dMdDd2NEVuRDRvWEQiLCJpYXQiOjE2NzE2OTQ1NTAsImV4cCI6MTY3MTczMDU1MCwic2lkIjoiOHo0cW9DUlhfRVVxVHB1TWdTcjBFNDVEbnpzMTJJY2QiLCJub25jZSI6IlRYbG9lR3BXTGs4eVgzVmhOMUF4YlZSVlFVVmhkSFJsTlZoRmIxSklkRmx1UzB4RE1HOTNVVTFWU2c9PSJ9.jO4vzmnz9opSNtuUUglJdJe1JoSTVRDP3S8PWAU7ucL8pK-pqt4fWfqtHbNaKvNpraKvQeB0P3wfvh_V-vVYriI6FDIXQgewgnJ9o8aUDF5UTr14enqp5JY8OOTTepMjItijLQ9B_tUQ7C1LBPJKwjrcHqvswe_Bp12LjR0vvNs7eDr59XqjGD4xXLWsWpD0PsfFMmoOD4omqA23JmAp6HsDHHVG_VSe8kIrncdUUz0Y_mHc9-nidst8IGfQExQHEXI3czc6AxMWswVwYBRKSx0-e9RrRgB96xbjv9B0uZ_KF9c1fAEaDcpIjX7h7RJWwKlkBPWrLKgqCfXAgvaMpg"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteInserat() throws Exception {
        Inserat inserat = inseratRepository
                .save(new Inserat("Titel", "Beschreibung", 23.90, KategorieEnum.ANDERE, new Person("test@gmail.com"), "file.png"));
        mockMvc.perform(
                delete("/api/inserat/delete/" + inserat.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbImFkbWluIl0sIm5pY2tuYW1lIjoia2xpbmNrcmkiLCJuYW1lIjoia2xpbmNrcmlAc3R1ZGVudHMuemhhdy5jaCIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9hYjI2NGU5OGY3NzQ3MjZlNTNkYzQ4ZGJlODYxNjFkNT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmtsLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTEyLTIyVDA3OjM1OjQ5LjEzNVoiLCJlbWFpbCI6ImtsaW5ja3JpQHN0dWRlbnRzLnpoYXcuY2giLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9kZXYtM2JjbDB1c3JpNWtvM2dnMC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjM4NWM5NzU2N2FkM2UwOTAzMGNkNThmIiwiYXVkIjoiWm1UblV3S1h6S0sxY3lTMnZZd3dMdDd2NEVuRDRvWEQiLCJpYXQiOjE2NzE2OTQ1NTAsImV4cCI6MTY3MTczMDU1MCwic2lkIjoiOHo0cW9DUlhfRVVxVHB1TWdTcjBFNDVEbnpzMTJJY2QiLCJub25jZSI6IlRYbG9lR3BXTGs4eVgzVmhOMUF4YlZSVlFVVmhkSFJsTlZoRmIxSklkRmx1UzB4RE1HOTNVVTFWU2c9PSJ9.jO4vzmnz9opSNtuUUglJdJe1JoSTVRDP3S8PWAU7ucL8pK-pqt4fWfqtHbNaKvNpraKvQeB0P3wfvh_V-vVYriI6FDIXQgewgnJ9o8aUDF5UTr14enqp5JY8OOTTepMjItijLQ9B_tUQ7C1LBPJKwjrcHqvswe_Bp12LjR0vvNs7eDr59XqjGD4xXLWsWpD0PsfFMmoOD4omqA23JmAp6HsDHHVG_VSe8kIrncdUUz0Y_mHc9-nidst8IGfQExQHEXI3czc6AxMWswVwYBRKSx0-e9RrRgB96xbjv9B0uZ_KF9c1fAEaDcpIjX7h7RJWwKlkBPWrLKgqCfXAgvaMpg"))
                .andExpect(status().isOk());

            Inserat inserat2 = inseratRepository
                .save(new Inserat("Titel", "Beschreibung", 23.90, KategorieEnum.ANDERE, new Person("test@gmail.com"), "file.png"));
        mockMvc.perform(
                delete("/api/inserat/delete/" + inserat2.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbXSwibmlja25hbWUiOiJjb2NvLmphbWJvIiwibmFtZSI6ImNvY28uamFtYm9AYWphamUuY2giLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvYWZhMzI3ZDU1MTY1NWQ1NzBlODY1MzFkMzRkOGNlNWE_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZjby5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMi0xMi0yMlQwNzo0MDo0My44NDJaIiwiZW1haWwiOiJjb2NvLmphbWJvQGFqYWplLmNoIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJpc3MiOiJodHRwczovL2Rldi0zYmNsMHVzcmk1a28zZ2cwLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2M2ExYmEwZDI2NjgyMjY0Y2FkNDgzYjIiLCJhdWQiOiJabVRuVXdLWHpLSzFjeVMydll3d0x0N3Y0RW5ENG9YRCIsImlhdCI6MTY3MTY5NDg0NCwiZXhwIjoxNjcxNzMwODQ0LCJzaWQiOiIwQU5YSWMtOUdzSGVHYTQ3NXVFdTAwZW9jeWFyWjVMTyIsIm5vbmNlIjoiVDNVNFgybHJjbWszVm5aR05FZFFlbWxsY25kdlExTnNTM05CYzIxNGJYRjRPVmxCVmpSRGVIZzRNQT09In0.JFo_1Jw3cZVGUmXjZXMvfa_QBGY2qxuV4IKFiM9WJu5j2z59DrS3o0fYES3hqfy-tR3cNSWzcD9Ut1Fih7_66cjSROSstgWW5Vp-rZ8wd_ZHTdevIpEo0WD2-YzhXd9kM5HR38eo_gDH9akT-ikmMX-8jP_sO3zAc_5ORw3k4VfDSFSTS9ikps1oymqoWTTS34AQ237dwku5IW8i5GWFAlMClq5C_hzI9_SG8mrNv4hoTH37zxkCP5V59ajfeEPKQ3nRVGjaCVM7uQMBktQSeN8UgejrAO2iuhbbfgG_uNh5mGuDlk8-_vqLDwMNLYqQ02XgVLAxexDeLdOTbt1glw"))
                .andExpect(status().isForbidden());
    }
}
