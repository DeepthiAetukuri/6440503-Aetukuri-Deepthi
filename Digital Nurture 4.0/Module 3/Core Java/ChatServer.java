package tcpchat;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Thread to read from client
            Thread receiveThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = input.readLine()) != null) {
                        System.out.println("Client: " + line);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });

            receiveThread.start();

            // Sending messages to client
            String msg;
            while ((msg = keyboard.readLine()) != null) {
                output.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
