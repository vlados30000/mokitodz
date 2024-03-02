package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;


class GeoServiceImplTest {
    private static
    GeoService geoService;

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("factory")
    void byIp(String ip, Location exсpextedLocation) {
        Location locationResult = geoService.byIp(ip);
        Assertions.assertEquals(exсpextedLocation, locationResult);
    }

    public static Stream<Arguments> factory() {
        Location locationNewYorkFull = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location location = new Location(null, null, null, 0);
        Location locationMoscowFull = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location locationNewYork = new Location("New York", Country.USA, null, 0);
        Location locationMoscow = new Location("Moscow", Country.RUSSIA, null, 0);
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, location),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, locationMoscowFull),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, locationNewYorkFull),
                Arguments.of("96.", locationNewYork),
                Arguments.of("172.", locationMoscow)
        );

    }
}