package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;

public class LocalizationServiceImplTests {

    @Test
    void localeTest (){
        Country country = RUSSIA;
        LocalizationService localizationService = new LocalizationServiceImpl();
        String result  = localizationService.locale(country);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected,result);
    }
}
