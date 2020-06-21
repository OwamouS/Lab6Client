package cmd;

import Client.ClientController;

import java.io.IOException;

public class CommandConnect implements Command, Local{

    private static final long serialVersionUID = 1337000003L;

    @Override
    public void execute(String[] args) throws IOException {
        if (args == null ||  args.length != 2){
            System.out.println("Please invoke this command with 2 arguments (IP, port).");
        }
        else {
            ClientController.changeDestIP(args[0]);
            ClientController.setDestPort(Integer.parseInt(args[1]));
        }
    }
}
