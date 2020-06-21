package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

public class Sender {

    public static void send (byte[] data){
        try {
            DatagramPacket commandPacket;
            DatagramPacket handle;
            byte[] approver;

            while(true) {
                if (data.length > 1012) {
                    commandPacket = new DatagramPacket(PacketFunctions.formatData(Arrays.copyOfRange(data, 0, 1012)),
                            1024, ClientController.getDestIP(), 1337);
                }
                else {
                    commandPacket = new DatagramPacket(PacketFunctions.formatData(Arrays.copyOf(data,1012)),
                            1024, ClientController.getDestIP(), 1337);
                }
                ClientController.getClientSocket().send(commandPacket);

                approver = new byte[1024];
                handle = new DatagramPacket(approver,1024);
                ClientController.getClientSocket().receive(handle);

                if ( handle.getData()[0] == 111 ){
                    if ( data.length > 1012 ) {
                        data = Arrays.copyOfRange(data, 1012, data.length);
                    }
                    else break;
                }
            }

            commandPacket.setData(new byte[1024]);
            ClientController.getClientSocket().send(commandPacket);

        }
        catch (SocketTimeoutException e){
            System.out.println("Server is not responding, please, try again later or change connection.");
        }
        catch (IOException e){
            System.out.println("Oh no, some IO ecxeption occurs, please, try again");
        }
    }
}
