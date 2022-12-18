package ch.zhaw.secondhandcloths.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import ch.zhaw.secondhandcloths.model.Root;

@Service
public class WeatherService {

    private static final String WEATHER_API_BASE_URL = "https://weatherapi-com.p.rapidapi.com/current.json";
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WebClient webClient;

    public WeatherService() {
        this.webClient = WebClient.builder()
                .baseUrl(WEATHER_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .filter(logRequest())
                .build();
    }

    public Root getConnection(String city) {
        return this.webClient
                .get()
                .uri("?q=" + city)
                .header("X-RapidAPI-Key", "cb5ad358femshe4a51716588b05ep1ec77ajsn6d8852d4d39b")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .retrieve()
                .bodyToMono(Root.class)
                .block(REQUEST_TIMEOUT);
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}