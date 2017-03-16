package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static final int PORT = 19000;
    public static final String HOST = "localhost";

    public static void main(String[] args) {

        Socket socket = null;

        for (int i = 0; i < 5; i ++) {

            try {
                socket = new Socket(HOST, PORT);

                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()) {

                    String string = "text to server";
                    out.write(string.getBytes());
                    out.flush();

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
