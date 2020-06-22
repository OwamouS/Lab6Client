package cliser;

import productdata.Product;

import java.io.Serializable;
import java.util.concurrent.ConcurrentSkipListSet;

public class Reply implements Serializable {

    private PlaceComparator comp = new PlaceComparator();
    private ConcurrentSkipListSet<Product> products = new ConcurrentSkipListSet<>(comp);
    private String answer;
    private static final long serialVersionUID = 1338L;

    public Reply(ConcurrentSkipListSet<Product> collection, String message){
        this.products = collection;
        this.answer = message;
    }

    public ConcurrentSkipListSet<Product> getProducts(){
        return products;
    }

    public String getAnswer(){
        return answer;
    }

}