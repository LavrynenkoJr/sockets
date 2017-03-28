package com.company;

import com.company.messageModels.Register;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static Random random = new Random();

    public static final int PORT = 19000;

    public static Map<Integer, Connection> connectionMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start server");

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {

                Socket socket = serverSocket.accept();

                Connection connection = new Connection(socket);
                connection.start();

                synchronized (connectionMap) {
                    connectionMap.wait();
                    Register register = new Register();
                    if (Server.connectionMap.size() > 1) {
                        Connection connectionWhoFirst = Server.connectionMap.get(random.nextInt(2) + 1);
                        connectionWhoFirst.sendRegister(register);
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
