package Client;

import cmd.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientController {

    private static DatagramSocket clientSocket = null;

    public static void takeCommand (Command command, String[] args){
        byte[] serializedCommand;
        byte[] serializedArgs;
        StringBuilder arguments = new StringBuilder("");
        if(args != null) {
            for (String line : args) {
                arguments.append(line);
                arguments.append(";");
            }
        }
        else arguments = null;
        byte[][] temp = CommandSerializer.serializeCommand(command,arguments != null ? arguments.toString():null);
        serializedCommand = temp != null ? temp[0] : new byte[0];
        serializedArgs = temp != null ? temp[1] : new byte[0];
        Sender.send(serializedCommand);
        Sender.send(serializedArgs);
        getReply();
    }

    public static void connect(){
        try {
            clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(1000);
        }
        catch (SocketException e){
            System.out.println("Cant connect");
        }
    }

    public static DatagramSocket getClientSocket(){
        return clientSocket;
    }

    public static void getReply() {
        byte[] buf = new byte[1024];
        try {
            while (true){
                DatagramPacket fromServer = new DatagramPacket(buf, 1024);
                clientSocket.receive(fromServer);
                ByteArrayInputStream bais = new ByteArrayInputStream(fromServer.getData());
                if (Arrays.equals(fromServer.getData(), new byte[1024])) {
                    break;
                }
                ObjectInputStream ois = new ObjectInputStream(bais);
                String result = (String) ois.readObject();
                System.out.print(result);
                DatagramPacket accept = new DatagramPacket(new byte[1],1024,InetAddress.getLocalHost(),1337);
            }
        }
        catch (SocketTimeoutException e){

        }
        catch (IOException e){

        }
        catch (ClassNotFoundException e){

        }

    }
}
