package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 19000;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                // ожидание входящего соединения
                Socket socket = serverSocket.accept();

                difClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void difClient(Socket sockett){

        final Socket socket = sockett;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()){

                    byte[] buf = new byte[32*1024];
                    int readBytes = in.read(buf);
                    String line = new String(buf, 0, readBytes);
                    System.out.println(line);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
