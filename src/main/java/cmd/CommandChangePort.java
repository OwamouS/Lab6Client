package cmd;

import Client.ClientController;

public class CommandChangePort implements Command, Local {

    private static final long serialVersionUID = 1337000001L;

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
