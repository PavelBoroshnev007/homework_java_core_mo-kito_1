package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeoServiceImplTests {
    private final GeoService geoService = new GeoServiceImpl();
    @ParameterizedTest
    @CsvSource({GeoServiceImpl.LOCALHOST + ",", GeoServiceImpl.MOSCOW_IP + ",RUSSIA", GeoServiceImpl.NEW_YORK_IP + ",USA"})
    void byIp(String ip, Country country) {
        assertEquals(geoService.byIp(ip).getCountry(), country);
    }

    @Test
    void byIpInvalidData() {
        assertNull(geoService.byIp("invalidData"));
    }
}
