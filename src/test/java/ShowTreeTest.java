import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class ShowTreeTest {

    ShowTree showTree = new ShowTree();

    @AfterAll
    public static void afterAll(){
        System.out.println("ShowTree test finished");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("another test");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("another test success");
    }
    @BeforeAll
    public static void beforeAll(){
        System.out.println("ShowTree test started");
    }

    @ParameterizedTest
    @MethodSource("showTreeSource")
    public void showTree(Integer[] tree, String expect){

        String actual = showTree.showTree(tree);

        Assertions.assertEquals(expect, actual);
    }

    public static Stream<Arguments> showTreeSource(){
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        Integer[] arr3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        return Stream.of(Arguments.of(arr1, "   2    \n" +
                                                       " 1   3  \n"),
                Arguments.of(arr2,  "       4        \n" +
                                              "   2       6    \n" +
                                              " 1   3   5   7  \n"),
                Arguments.of(arr3, "               8                \n" +
                                              "       4              12        \n" +
                                              "   2       6      10      14    \n" +
                                              " 1   3   5   7   9  11  13  15  \n"));
    }
}
