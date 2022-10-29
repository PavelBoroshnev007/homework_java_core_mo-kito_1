import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;

public class MessageSenderTests {

    private static final String MESSAGE_RUSSIA = "Добро пожаловать";
    private static final String MESSAGE_USA = "Welcome";



    @Test
    void test_send_Russia (){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "street1", 1));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(MESSAGE_RUSSIA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);
        String result = messageSender.send(headers);
        Assertions.assertEquals(MESSAGE_RUSSIA,result);

    }

    @Test
    void test_send_USA (){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, "street2", 2));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(MESSAGE_USA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP);
        String result = messageSender.send(headers);
        Assertions.assertEquals(MESSAGE_USA,result);

    }
}
