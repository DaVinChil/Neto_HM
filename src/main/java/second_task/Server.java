package second_task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("New connection accepted");

            out.println("Write your name");
            final String name = in.readLine();
            out.println("Are you child? (yes/no)");
            String resp = in.readLine();
            if (resp.equals("yes")) {
                out.printf("Welcome, to the kids area, %s Let's play!\n", name);
            } else {
                out.printf("Welcome, to the adult zone, %s! Have a good rest, or a good working day!\n", name);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}