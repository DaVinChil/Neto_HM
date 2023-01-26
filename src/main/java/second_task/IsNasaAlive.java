package second_task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URL;

public class IsNasaAlive {
    public static void main(String[] args) {

        NasaRespond nasaResp = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=GGNxyhFejN6kxWlfHTVT8pydzNydL5De0haFGQwG");
            CloseableHttpResponse response = httpClient.execute(request);
            String jsonResponse = new String(response.getEntity().getContent().readAllBytes(), Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            nasaResp = objectMapper.readValue(jsonResponse, NasaRespond.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String extension = nasaResp.getUrl().substring(nasaResp.getUrl().lastIndexOf("."));

        try (BufferedInputStream in = new BufferedInputStream(new URL(nasaResp.getUrl()).openStream());
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("nasa" + extension)));) {
            out.write(in.readAllBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        File file = new File("nasa" + extension);
        if (file.canRead()) {
            System.out.println("File can be opened");
        } else {
            System.out.println("File can not be opened");
        }
    }
}
