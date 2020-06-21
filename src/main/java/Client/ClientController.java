package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientController {

    private static DatagramSocket clientSocket = null;
    private static InetAddress destIP = null;
    private static int destPort = 1337;

    public static Reply handleRequest(Request request){
        byte[] serializedRequest = Serializer.serialize(request);
        assert serializedRequest != null;
        byte[] reply = null;
        try {
            Sender.send(serializedRequest);
            reply = Receiver.getReply();
        }
        catch(SocketTimeoutException e){
            System.out.println("Server is not responding, please, try again later or change connection.");
            return null;
        }
        catch (IOException e){
            System.out.print("Oh no, some IO Exception occurs, please, try again.");
        }
        return Serializer.deserialize(reply);
    }

    public static void connect(){
        try {
            clientSocket = new DatagramSocket(1338);
            clientSocket.setSoTimeout(10000);
            changeDestIP("localhost");
            System.out.println("Port has been successfully changed.");
        }
        catch (BindException e){
            System.out.println("Your port already in use, please choose another port:");
            int port = changePort();
            connect(port);
        }
        catch (SocketException e){
            System.out.println("Port is unavailable, please choose another port:");
            int port = changePort();
            connect(port);
        }
    }

    public static int changePort() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int port;
        while (true) {
            try {
                String p = reader.readLine();

                port = Integer.parseInt(p);
                break;
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
                System.out.println("Please enter a correct port:");
            }
        }
        return port;
    }

    public static void connect(int number){
        try {
            clientSocket = new DatagramSocket(number);
            clientSocket.setSoTimeout(1000);
            changeDestIP("localhost");
            System.out.println("Port has been successfully changed.");
        }
        catch (BindException e){
            System.out.println("Your port already in use, please choose another port:");
            int port = changePort();
            connect(port);
        }
        catch (SocketException e){
            System.out.println("Port is unavailable, please choose another port:");
            int port = changePort();
            connect(port);
        }
    }

    public static void connect(int number, String IP){
        try {
            clientSocket = new DatagramSocket(number);
            clientSocket.setSoTimeout(1000);
            changeDestIP(IP);
            System.out.println("Port has been successfully changed.");
        }
        catch (BindException e){
            System.out.println("Your port already in use, please choose another port:");
            int port = changePort();
            connect(port);
        }
        catch (SocketException e){
            System.out.println("Port is unavailable, please choose another port:");
            int port = changePort();
            connect(port);
        }
    }

    public static DatagramSocket getClientSocket(){
        return clientSocket;
    }

    public static InetAddress getDestIP() {
        return destIP;
    }

    public static void changeDestIP(String name){
        try {
            destIP = InetAddress.getByName(name);
            System.out.println("IP has been successfully changed.");
        }
        catch (UnknownHostException e){
            System.out.println("IP address is not determined, please enter correct IP address:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String IP;
            while (true) {
                try {
                    IP = reader.readLine();
                    break;
                } catch (IOException ex) {
                    System.out.println("Please enter a correct port:");
                }
            }
            changeDestIP(IP);
        }
    }

    public static void setDestPort(int destPort) {
        ClientController.destPort = destPort;
        System.out.println("Port has been successfully changed.");
    }

    public static int getDestPort() {
        return destPort;
    }
}


