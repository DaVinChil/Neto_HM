package first_task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String jsonResult = RetriveFactsAboutCats.getCatFacts();
        ObjectMapper objectMapper = new ObjectMapper();
        List<CatPost> catPosts = objectMapper.readValue(jsonResult, new TypeReference<>() {});
        catPosts.stream()
                .filter(x -> x.getUpvotes() != null && x.getUpvotes() > 0)
                .forEach(System.out::println);
    }
}
