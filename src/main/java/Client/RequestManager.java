package Client;

import cmd.Command;
import cmd.Local;
import cmd.Preparable;
import java.io.IOException;

public class RequestManager {

    public static void makeRequest(Command command, String[] args){

        Command cmd = null;
        try {
            cmd = command.getClass().newInstance();
        }
        catch (InstantiationException e){
            System.out.println("Command have not nullary constructor.");
        }
        catch (IllegalAccessException e){
            System.out.println("Why do we lost access to cmd ?");
        }
        if (command instanceof Local){
            try {
                command.execute(args);
            }
            catch (IOException e){
                System.out.println("Wrong arguments");
            }
            return;
        }
        if (command instanceof Preparable){
            if (cmd != null) {
                ((Preparable) cmd).prepare(args);
            }
        }
        Reply result = ClientController.handleRequest(new Request(command, args));
        if (result != null) {
            System.out.println(result.getResult());
        }

    }
}
