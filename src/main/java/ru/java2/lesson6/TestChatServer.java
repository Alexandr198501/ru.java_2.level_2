package ru.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может
писать сообщения как на клиентской стороне, так и на серверной. Т.е. если на клиентской
стороне написать «Привет», нажать Enter, то сообщение должно передаться на сервер и там
отпечататься в консоли. Если сделать то же самое на серверной стороне, сообщение,
соответственно, передаётся клиенту и печатается у него в консоли. Есть одна особенность,
которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
Такую ситуацию необходимо корректно обработать.
Разобраться с кодом с занятия — он является фундаментом проекта-чата.
ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать
второго/третьего/n-го клиента.
 */
public class TestChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Server start, wait connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String strFromClient = in.readUTF();
                        System.out.println("Клиент отправил: " + strFromClient);
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

