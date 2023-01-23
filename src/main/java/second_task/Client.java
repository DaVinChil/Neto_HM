package second_task;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        int port = 8080;
        String host = "netology.homework";
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while(true){
                String resp = in.readLine();
                System.out.println(resp);
                out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}