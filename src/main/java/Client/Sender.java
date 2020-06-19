package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

public class Sender {

    public static void send (byte[] data){
        try {
            DatagramPacket commandPacket;
            DatagramPacket handle;
            byte[] approver;

            while(true) {
                if (data.length > 1012) {
                    commandPacket = new DatagramPacket(formatData(Arrays.copyOfRange(data, 0, 1012)),
                            1024, InetAddress.getByName(ClientController.getInetIP()), 1337);
                }
                else {
                    commandPacket = new DatagramPacket(formatData(Arrays.copyOf(data,1012)),
                            1024, InetAddress.getByName(ClientController.getInetIP()), 1337);
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
        catch (IOException e){

        }
    }

    protected static byte[] sepOnBytes(int code){
        byte[] res = new byte[11];
        int i = 0;
        while( code / 10 > 0 ){
            res[i] = (byte) (code % 10);
            code = code / 10;
            i++;
        }
        res[i] = (byte) (code);
        res[i+1] = 111;
        return res;
    }

    private static int uniteIntoInt( byte[] data ){
        int res = 0;
        int i = 0;
        while ( data[i] != 111 ){
            res += data[i]*Math.pow(10,i);
            i++;
        }
        return res;
    }

    private static byte[] formatData( byte[] data ){
        int hashCode  = Arrays.hashCode(data);
        byte[] result = Arrays.copyOf(data,data.length + 12);
        if (hashCode > 0 )
            result[data.length] = 1;
        else
            result[data.length] = -1;
        byte[] arrCode = sepOnBytes(Math.abs(hashCode));
        int i = 1;
        for (byte b: arrCode){
            result[data.length+i] = b;
            i++;
        }
        return result;
    }
}
