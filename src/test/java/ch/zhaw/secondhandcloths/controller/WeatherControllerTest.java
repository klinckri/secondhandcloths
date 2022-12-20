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
                        + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlozSzhpSXZMQ2EyWV9QVmo0SDdRNSJ9.eyJ1c2VyX3JvbGVzIjpbImFkbWluIl0sIm5pY2tuYW1lIjoia2xpbmNrcmkiLCJuYW1lIjoia2xpbmNrcmlAc3R1ZGVudHMuemhhdy5jaCIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9hYjI2NGU5OGY3NzQ3MjZlNTNkYzQ4ZGJlODYxNjFkNT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmtsLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTEyLTIwVDE1OjA0OjAwLjM0MVoiLCJlbWFpbCI6ImtsaW5ja3JpQHN0dWRlbnRzLnpoYXcuY2giLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9kZXYtM2JjbDB1c3JpNWtvM2dnMC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjM4NWM5NzU2N2FkM2UwOTAzMGNkNThmIiwiYXVkIjoiWm1UblV3S1h6S0sxY3lTMnZZd3dMdDd2NEVuRDRvWEQiLCJpYXQiOjE2NzE1NDg2NDEsImV4cCI6MTY3MTU4NDY0MSwic2lkIjoiNk9XMGxfRTNndHZkVnRlRkZ6dGxCUzBHbmJwRWY2U3QiLCJub25jZSI6IlR6SmpaREZzTUdGaU1uRTNjSEZXWjNjMk4ybzVSMGRyTlU5dFRsVkNUbjVPV1ZKWGRVRmFRa2RGU2c9PSJ9.SXLtTire3Nm_Bm_dHseWXyk_B_BjAeH1mt66K91QRnnFRiP8BXO6fswIhB3-A9d-gIXNz8g6Eoi3xvL2khJ4L9_KqRv_NeUrPynGRhKj82LApo_46DsJ5oXxj4lLR9e9HD-ZPS3Wj2siqdHgrORopJrdEHEeeYVoxydao6LacO-ga-kgzJnUAWvqPJGC8_MFN2u1d_U4bSVLtQJQ0Z-igd3gJmY7vlxfGItpKOQ8EKPTOCT5J-x0DDLTHP_gYVarrwL1xgpLt7jFZA4Ig_w0xccrqZ7faUAhT6AFCtFDN6LR6Msht0FD0jDc1XbK2UKb5pnHT4BFVzZbpX67Qkd4Nw"))
                .andExpect(status().isOk());
    }
}
