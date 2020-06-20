package Client;

import cmd.Command;
import cmd.CommandAdd;
import cmd.Preparable;

public class RequestManager {

    public static void makeRequest(Command command, String[] args){

        try {
            Command cmd = command.getClass().newInstance();
        }
        catch (InstantiationException e){
            System.out.println("Command have not nullary constructor.");
        }
        catch (IllegalAccessException e){
            System.out.println("Why do we lost access to cmd ?");
        }
        if (command instanceof Preparable){
            ((Preparable) command).prepare(args);
        }
        Reply result = ClientController.handleRequest(new Request(command, args));
        System.out.println(result.getResult());

    }
}
