package Client;

import cmd.Command;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

public class Sender {

    public static void send (byte[] data){
        try {
            while(data.length >1024) {
                DatagramPacket commandPacket = new DatagramPacket(Arrays.copyOfRange(data,0,1024),
                        data.length, InetAddress.getLocalHost(), 1337);
                data = Arrays.copyOfRange(data,1024,data.length);
                ClientController.getClientSocket().send(commandPacket);
                byte[] approver = new byte[1024];
                DatagramPacket handle = new DatagramPacket(approver,1024);
                ClientController.getClientSocket().receive(handle);
            }
            DatagramPacket commandPacket = new DatagramPacket(data,
                    data.length, InetAddress.getLocalHost(), 1337);
            ClientController.getClientSocket().send(commandPacket);
            byte[] approver = new byte[1024];
            DatagramPacket handle = new DatagramPacket(approver,1024);
            ClientController.getClientSocket().receive(handle);
        }
        catch (IOException e){

        }
    }
}
