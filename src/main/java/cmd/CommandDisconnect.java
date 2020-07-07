package cmd;

import Client.ClientController;

import java.io.IOException;
import java.io.Serializable;

/**
 * break the programm
 *
 *
 */

public class CommandDisconnect implements Command, Serializable {

    private static final long serialVersionUID = 13370000020L;

    @Override
    public String execute(String[] args){
        try {
            if (args.length == 1) {
                System.out.println("There is no args for this command!");
            }
        }catch (NullPointerException e) {
            ClientController.getClientSocket().close();
            return "disconnect";
        }
        return null;
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "exit";
    }
}
