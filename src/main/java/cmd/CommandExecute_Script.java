package cmd;

import Control.ScriptParser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * execute command from script
 *
 *
 */

public class CommandExecute_Script implements Command, Preparable{

    private static final long serialVersionUID = 1337000004L;

    ArrayList<String[]> commands;

    @Override
    public void execute(String[] args) throws IOException {
        if (commands == null){
            ScriptParser.parseScript(args[0]);
            execute(args);
        }
        else {
            ScriptParser.executeQuery(commands);
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "execute_script";
    }

    @Override
    public void prepare(String[] args) {
        this.commands = ScriptParser.parseScript(args[0]);
    }
}
