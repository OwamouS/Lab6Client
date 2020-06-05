package Client;

import cmd.Command;

import java.io.*;

public class CommandSerializer {

    public static byte[][] serializeCommand(Command command,String args){
        byte[][] buff = new byte[2][];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();

            buff[0] = baos.toByteArray();

            baos.reset();
            objectOutputStream.writeObject(args);
            objectOutputStream.flush();

            buff[1] = baos.toByteArray();
            return buff;
        }
        catch (IOException e){
            System.out.println("Oh no");
            return null;
        }
    }

    public static byte[] serializeCommand(String command){
        byte[] buff;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();

            buff = baos.toByteArray();
            return buff;
        }
        catch (IOException e){
            System.out.println("Oh no");
            return null;
        }
    }
}
