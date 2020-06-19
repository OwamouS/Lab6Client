package Client;

import cmd.Command;

import java.io.*;
import java.util.Arrays;

public class CommandSerializer {

    public static byte[] serializeCommand(Command command){
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
