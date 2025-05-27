package tcpchat;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Thread to read from server
            Thread receiveThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = input.readLine()) != null) {
                        System.out.println("Server: " + line);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });

            receiveThread.start();

            // Sending messages to server
            String msg;
            while ((msg = keyboard.readLine()) != null) {
                output.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

