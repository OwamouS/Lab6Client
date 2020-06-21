package cmd;
import Control.TableController;
import Control.TableManager;

import java.util.Hashtable;

/**
 * gives info about date of collection's creation, collection's size and collection's type
 *
 *
 */

public class CommandInfo implements Command {

    private static final long serialVersionUID = 1337000010L;

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 1) {
                System.out.println("There is no args for this command!");
            }
        }catch (NullPointerException e) {
            System.out.println("Size of collection: " + TableController.getCurrentTable().getSize() + "\n"
                    + "Type of collection: " + TableController.getCurrentTable().getType() + "\n" +
                    "Date of creation: " + TableController.getCurrentTable().getCreationDate().toString());
            System.out.println("Command complete...");
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    public String toString(){
        return "info";
    }
}
