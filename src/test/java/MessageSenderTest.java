import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Matches;
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
import java.util.stream.Stream;

public class MessageSenderTest {
    public MessageSender messageSender;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Running tests");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Finished tests");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Before test");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("After test");
    }

    @Test
    public void russianTest() {
        String expected = "Добро пожаловать";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        GeoService geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        messageSender = new MessageSenderImpl(geoService, localizationService);

        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void englishTest() {
        String expected = "Welcome";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");

        GeoService geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        messageSender = new MessageSenderImpl(geoService, localizationService);

        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("locationByIpSource")
    public void locationByIp(String ip, Location expected){
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location actual = geoService.byIp(ip);
        if(expected != null && actual != null) {
            Assertions.assertEquals(expected.getCountry(), actual.getCountry());
            Assertions.assertEquals(expected.getBuiling(), actual.getBuiling());
            Assertions.assertEquals(expected.getStreet(), actual.getStreet());
            Assertions.assertEquals(expected.getCity(), actual.getCity());
        } else {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static Stream<Arguments> locationByIpSource(){
        return Stream.of(Arguments.of("172.1.54.33", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("s15142", null),
                Arguments.of("96.22.333.444", new Location("New York", Country.USA, null , 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)));

    }

    @ParameterizedTest
    @MethodSource("localeSource")
    public void locale(Country country, String expected){
        LocalizationServiceImpl lcl = new LocalizationServiceImpl();

        String actual = lcl.locale(country);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> localeSource(){
        return Stream.of(Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"));
    }
}
