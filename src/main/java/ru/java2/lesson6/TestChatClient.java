package ru.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestChatClient {
    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 8189;
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            System.out.println("Client connected!");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        System.out.println("Сервер отправил: " + strFromServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                Scanner sc = new Scanner(System.in);
                out.writeUTF(sc.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
