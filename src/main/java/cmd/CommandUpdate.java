package cmd;

import Control.TableController;
import productdata.Product;
import productdata.ReaderProductBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Update whole element with given id
 *
 *
 */

public class CommandUpdate implements Command, Preparable{

    /**
     * Iterates through all elements of collection and update element with given id
     *
     *
     */

    Product product = null;
    private static final long serialVersionUID = 1337000016L;

    @Override
    public void execute(String[] args) {

        if (product == null){
            prepare(args);
            execute(args);
        }
        else {
            try {
                if (args[0] == null) {
                    System.out.println("Please enter ID");
                }
                int counter = 0;
                Iterator<Map.Entry<String, Product>> it = TableController.getCurrentTable().getSet().iterator();
                int i = Integer.parseInt(args[0]);
                while (it.hasNext()) {
                    Map.Entry<String, Product> map = it.next();
                    if (map.getValue().getId() == i) {
                        counter++;
                        TableController.getCurrentTable().replace(map.getKey(), product);
                    }
                }
                if (counter == 0) {
                    System.out.println("There is no elements with that id.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Argument must be a number");
            }
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "update_id";
    }

    @Override
    public void prepare(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.product = ReaderProductBuilder.buildProduct(reader);
    }
}
