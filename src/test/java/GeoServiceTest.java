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

public class GeoServiceTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Running GeoServiceImpl tests");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Finished GeoServiceImpl tests");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Before test");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("After test");
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
}
