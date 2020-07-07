package Control;

import cmd.CommandHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that works with console input
 */
public class CommandController {
    /**
     * Field which define works this controller or not, sets to true when controller starts and sets to false when its stops
     */
    private boolean isOn;
    /**
     * Field that created to save a history of completed commands
     */
    private  static CommandHistory commandHistory = new CommandHistory();

    /**
     * Starts the controller. When controller is working user can enter a commands into console.
     * Controller takes command and split it on String array (command and arguments), then throw it to Interpreter
     * @param interpreter Interpreter, which controller will use to interpret commands
     */
    public void start(Interpreter interpreter){
        isOn = true;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            while (isOn){
                if(line == null){
                    System.exit(0);
                 }
                if(!"".equals(line)) {
                    interpreter.handle(line.split(" "));
                    System.out.println("\nEnter command:");
                }
                if(line.equals("help")){
                    System.out.println("help: display help for available commands \n " +
                            "info: display information about the collection (type, initialization date, number of elements, etc.) in the standard output stream \n" +
                            "show: output to the standard output stream all the elements of the collection in the string representation \n" +
                            "insert key {element}: add a new element with the given key \n" +
                            "update id {element}: update the value of a collection element whose id is equal to the given \n" +
                            "remove_key key: remove an item from the collection by its key \n" +
                            "clear: clear collection \n" +
                            "change_port: change current client port \n" +
                            "connect IP port: connect to new socket \n" +
                            "execute_script file_name: read and execute the script from the specified file. The script contains the commands in the same form in which the user types them interactively. \n" +
                            "exit: exit the program (without saving to a file) \n" +
                            "remove_lower {element}: remove from the collection all elements which ID is smaller than the specified \n" +
                            "history: print the last 7 commands (without their arguments) \n" +
                            "replace_if_greater key {element}: replace the value by key, if the price of new value is greater than the old \n" +
                            "min_by_name: output any object from the collection whose name field value is minimal \n" +
                            "group_counting_by_coordinates: group the elements of the collection by the value of the coordinates field, display the number of elements in each group \n" +
                            "filter_less_than_manufacturer manufacturer: display elements whose manufacturer ID field value is less than the specified");
                }
                System.out.print(">");
                line = reader.readLine();
            }
        }
        catch (IOException e){
            System.out.println("Invalid symbol sequence, enter correct command or enter help to get a list of commands...");
        }
    }

    /**
     * Stops the session of Controller
     */
    public void stop(){
        isOn = false;
    }

    /**
     * Returns the Command History
     * @return Command History
     */
    public static CommandHistory getCommandHistory(){
        return commandHistory;
    }

}