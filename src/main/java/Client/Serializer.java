package Client;

import java.io.*;

public class Serializer {

    protected static <T> byte[] serialize(T obj){
        byte[] buff;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            buff = baos.toByteArray();
            return buff;
        }
        catch (IOException e){
            //make
            return null;
        }
    }

    protected static Reply deserialize(byte[] data){
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Reply) ois.readObject();
        }
        catch (IOException ioException) {
        }
        catch (ClassNotFoundException classNotFoundException){
        }

        return null;
    }
    
}
