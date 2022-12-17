package ch.zhaw.secondhandcloths.service;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import ch.zhaw.secondhandcloths.model.Language;

@Service
public class ConnectionService {

    private static final String OPENDATA_TRANSPORT_BASE_URL = "https://google-translate1.p.rapidapi.com/language/translate/v2/languages";
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private final WebClient webClient;

    public ConnectionService() {
        this.webClient = WebClient.builder()
                .baseUrl(OPENDATA_TRANSPORT_BASE_URL)
                .build();
    }

    public Language getConnection(String city) {
        return webClient
                .get() // http-method
               // .uri("?q=" + city) // uri to api
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Key", "cb5ad358femshe4a51716588b05ep1ec77ajsn6d8852d4d39b")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .retrieve()
                .bodyToMono(Language.class)
                .block(REQUEST_TIMEOUT);
    }
}
