package ch.zhaw.secondhandcloths.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testConnection() throws Exception {
        mockMvc.perform(get("/api/weather").param("city", "Bern")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "
                        + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbImFkbWluIl0sIm5pY2tuYW1lIjoia2xpbmNrcmkiLCJuYW1lIjoia2xpbmNrcmlAc3R1ZGVudHMuemhhdy5jaCIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9hYjI2NGU5OGY3NzQ3MjZlNTNkYzQ4ZGJlODYxNjFkNT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmtsLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTEyLTIyVDA3OjM1OjQ5LjEzNVoiLCJlbWFpbCI6ImtsaW5ja3JpQHN0dWRlbnRzLnpoYXcuY2giLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9kZXYtM2JjbDB1c3JpNWtvM2dnMC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjM4NWM5NzU2N2FkM2UwOTAzMGNkNThmIiwiYXVkIjoiWm1UblV3S1h6S0sxY3lTMnZZd3dMdDd2NEVuRDRvWEQiLCJpYXQiOjE2NzE2OTQ1NTAsImV4cCI6MTY3MTczMDU1MCwic2lkIjoiOHo0cW9DUlhfRVVxVHB1TWdTcjBFNDVEbnpzMTJJY2QiLCJub25jZSI6IlRYbG9lR3BXTGs4eVgzVmhOMUF4YlZSVlFVVmhkSFJsTlZoRmIxSklkRmx1UzB4RE1HOTNVVTFWU2c9PSJ9.jO4vzmnz9opSNtuUUglJdJe1JoSTVRDP3S8PWAU7ucL8pK-pqt4fWfqtHbNaKvNpraKvQeB0P3wfvh_V-vVYriI6FDIXQgewgnJ9o8aUDF5UTr14enqp5JY8OOTTepMjItijLQ9B_tUQ7C1LBPJKwjrcHqvswe_Bp12LjR0vvNs7eDr59XqjGD4xXLWsWpD0PsfFMmoOD4omqA23JmAp6HsDHHVG_VSe8kIrncdUUz0Y_mHc9-nidst8IGfQExQHEXI3czc6AxMWswVwYBRKSx0-e9RrRgB96xbjv9B0uZ_KF9c1fAEaDcpIjX7h7RJWwKlkBPWrLKgqCfXAgvaMpg"))
                .andExpect(status().isOk());
    }
}
