package ch.zhaw.secondhandcloths.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ch.zhaw.secondhandcloths.model.Root;

public class WeatherServiceTest {

    @Test
    void testGetConnection() {
        WeatherService weatherService = new WeatherService();
        Root root = weatherService.getConnection("Bern");
        assertNotNull(root);
        assertNotNull(root.getCurrent());
        assertNotNull(root.getLocation());
    }
}
