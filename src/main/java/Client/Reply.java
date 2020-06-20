package Client;

import java.io.Serializable;

public class Reply implements Serializable {

    String result;

    public Reply(String result){
        this.result = result;
    }

    protected String getResult() {
        return result;
    }
}
