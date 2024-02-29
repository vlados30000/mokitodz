package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private static
    LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("factory")
    void locale(Country country, String exсpeted) {
        String result = localizationService.locale(country);
        Assertions.assertEquals(exсpeted, result);
    }

    public static Stream<Arguments> factory() {
        return Stream.concat(
                Stream.of(
                        Arguments.of(Country.RUSSIA, "Добро пожаловать")
                ),
                Arrays.stream(Country.values())
                        .filter(v -> v != Country.RUSSIA)
                        .map(v -> Arguments.of(v, "Welcome"))
        );

    }


}