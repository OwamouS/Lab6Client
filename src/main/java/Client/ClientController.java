package Client;

import cmd.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.Arrays;

public class ClientController {

    private static DatagramSocket clientSocket = null;
    private static String inetIP = "localhost";

    public static void takeCommand (Command command, String[] args){
        byte[] serializedCommand = CommandSerializer.serializeCommand(command);
        Sender.send(serializedCommand);
        getReply();
    }

    public static void connect(){
        try {
            clientSocket = new DatagramSocket(1338);
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
        byte[] clear = new byte[1024];
        byte[] bad = new byte[1024];
        clear[0] = 111;
        bad[0] = 22;
        try {
            byte[] result = new byte[0];
            while (true) {

                DatagramPacket fromServer = new DatagramPacket(buf, 1024);
                clientSocket.receive(fromServer);

                if (Arrays.equals(fromServer.getData(), new byte[1024])) {
                    break;
                }

                if (checkHash(fromServer.getData())) {
                    DatagramPacket toServer = new DatagramPacket(clear,
                            1024, InetAddress.getByName(inetIP), 1337);
                    clientSocket.send(toServer);
                    result = merge(result,Arrays.copyOfRange(fromServer.getData(),0,1012));
                }
                else {
                    DatagramPacket toServer = new DatagramPacket(bad,
                            1024, InetAddress.getByName(inetIP), 1337);
                    clientSocket.send(toServer);
                }
            }
        }
        catch(SocketTimeoutException e){
            }
        catch(IOException e){
            }
    }

    protected static boolean checkHash(byte[] data){
        int hashCode = Arrays.hashCode(Arrays.copyOfRange(data,0,1012));
        int i = 0;
        int code = 0;
        while ( data[1013 + i] != 111){
            code += data[1013 + i] * Math.pow(10,i);
            i++;
        }
        return (hashCode == code * data[1012]);
    }

    public static String getInetIP() {
        return inetIP;
    }

    protected static byte[] merge(byte[] a1, byte[] a2){
        byte[] result = Arrays.copyOf(a1,a1.length + a2.length);
        int i = 0;
        for (byte b : a2){
            result[a1.length + i] = b;
            i++;
        }
        return result;
    }
}
