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

public class LocalizationServiceTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Running LocalizationServiceImpl tests");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Finished LocalizationServiceImpl tests");
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
