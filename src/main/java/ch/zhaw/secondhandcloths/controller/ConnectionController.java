package ch.zhaw.secondhandcloths.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import ch.zhaw.secondhandcloths.model.Language;
import ch.zhaw.secondhandcloths.service.ConnectionService;

@RestController
@RequestMapping("/api/weather")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping("")
    public ResponseEntity<Language> connection(@RequestParam String city) {
        System.out.println("I'm here");
        Language connection = connectionService.getConnection(city);

        return new ResponseEntity<>(connection, HttpStatus.OK);

    }



}
