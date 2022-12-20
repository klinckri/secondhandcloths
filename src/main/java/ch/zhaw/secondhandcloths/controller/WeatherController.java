package ch.zhaw.secondhandcloths.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ch.zhaw.secondhandcloths.model.Root;
import ch.zhaw.secondhandcloths.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService connectionService;

    @GetMapping("")
    public ResponseEntity<Root> connection(@RequestParam String city) {
        Root connection = connectionService.getConnection(city);
        return new ResponseEntity<>(connection, HttpStatus.OK);
    }
}
