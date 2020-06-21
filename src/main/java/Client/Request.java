package Client;

import cmd.Command;

import java.io.Serializable;

public class Request implements Serializable {

    String[] args;
    Command command;
    private static final long serialVersionUID = 1337L;

    public Request(Command cmd, String[] args){
        this.args = args;
        this.command = cmd;
    }

}
