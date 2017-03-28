package com.company;

import java.io.*;
import java.net.Socket;

public class Client {

    private static int clientId = 1;
    private static int toClientId = 2;

    public static final int PORT = 19000;
    public static final String HOST = "localhost";


    public static void main(String[] args) {

        Socket socket = null;

        Fighter fighter = new Fighter(1);

        try {
            socket = new Socket(HOST, PORT);

            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

               /* After connection socket, client sending REGISTER message with id to srever
                * if server response with Register object
                * this Client will hit first
                * if server response with Message object
                * fighter tries to bias and hit back
                * (Message - object with hit)
                */

                Register register = new Register(clientId);
                out.writeObject(register);
                out.flush();

                while (true) {

                    try {
                        Object object = in.readObject();

                        if (object instanceof Register){
                            Massage massage = new Massage(clientId, toClientId, fighter.hit(), fighter.getHealth());
                            out.writeObject(massage);
                            out.flush();
                        }

                        if (object instanceof Massage){
                            Massage inputMas = (Massage) object;
                            fighter.damage(inputMas.getHit());
                            System.out.println("Вас ударил = " + inputMas.getFromClientId() + " урон = " + inputMas.getHit());

                            Massage massage = new Massage(clientId, toClientId, fighter.hit(), fighter.getHealth());
                            out.writeObject(massage);
                            out.flush();


                        }else if (object instanceof FightResult){
                            System.out.println("fight result");
                            FightResult fightResult = (FightResult) object;
                            System.out.println(fightResult.getResult());

                            break;

                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                out.close();
                in.close();
                socket.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
