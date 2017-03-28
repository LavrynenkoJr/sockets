package com.company;

import com.company.messageModels.FightResult;
import com.company.messageModels.Massage;
import com.company.messageModels.Register;

import java.io.*;
import java.net.Socket;

/**
 * Created by Cyborg on 3/27/2017.
 */
public class Connection extends Thread {

    private final Socket socket;
    private ObjectOutputStream outputStream = null;

    public Connection(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Connection client");
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
            outputStream = new ObjectOutputStream(socket.getOutputStream());


            while (true) {
                Massage massage = null;
                Register register = null;
                try {

                    Object object = in.readObject();

                    if (object instanceof Register){
                        register = (Register) object;
                        synchronized (Server.connectionMap) {
                            Server.connectionMap.put(register.getClientId(), this);
                            Server.connectionMap.notify();
                        }

                        System.out.println("Registration figther with id=" + register.getClientId());

                    }

                    if (object instanceof Massage){
                        massage = (Massage) object;

                        if (massage.getHp()>0){
                            System.out.println("fighter = " + massage.getFromClientId() + " HIT TO fighter = " + massage.getToClientId());

                            Connection connectionToSend = Server.connectionMap.get(massage.getToClientId());
                            connectionToSend.sendMassage(massage);

                        }else {
                            FightResult fightResultLos = new FightResult("Lose");
                            outputStream.writeObject(fightResultLos);
                            outputStream.flush();

                            FightResult fightResultWin = new FightResult("Win");
                            Connection connectionToSend = Server.connectionMap.get(massage.getToClientId());
                            connectionToSend.sendResult(fightResultWin);
                            break;
                        }
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("close");
            in.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMassage(Massage massage){

        try {
            outputStream.writeObject(massage);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResult(FightResult fightResult){

        try {
            System.out.println("Sending fight result");
            outputStream.writeObject(fightResult);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRegister(Register register){
        try {
            System.out.println("Sending who hit first");
            outputStream.writeObject(register);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
