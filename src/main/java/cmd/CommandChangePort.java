package cmd;

import Client.ClientController;

public class CommandChangePort implements Command, Local {

    @Override
    public void execute(String[] args){
        if (args == null || args.length != 1) {
            ClientController.connect(Integer.parseInt(args[0]));
        }
        else {
            System.out.println("Wrong arguments");
        }
    }
}
