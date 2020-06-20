package Client;

import java.net.*;

public class ClientController {

    private static DatagramSocket clientSocket = null;
    private static InetAddress destIP = null;

    public static Reply handleRequest(Request request){
        byte[] serializedRequest = Serializer.serialize(request);
        assert serializedRequest != null;
        Sender.send(serializedRequest);
        byte[] reply = Receiver.getReply();
        return Serializer.deserialize(reply);
    }

    public static void connect(){
        try {
            clientSocket = new DatagramSocket(1338);
            clientSocket.setSoTimeout(1000);
            changeDestIP("localhost");
        }
        catch (SocketException e){
        }
    }

    public static void connect(int number){
        try {
            clientSocket = new DatagramSocket(number);
            clientSocket.setSoTimeout(1000);
            changeDestIP("localhost");
        }
        catch (SocketException e){
        }
    }

    public static void connect(int number, String IP){
        try {
            clientSocket = new DatagramSocket(number);
            clientSocket.setSoTimeout(1000);
            changeDestIP(IP);
        }
        catch (SocketException e){
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
        }
        catch (UnknownHostException e){

        }
    }
}
