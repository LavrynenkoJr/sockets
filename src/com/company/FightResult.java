package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by Cyborg on 3/27/2017.
 */
public class FightResult implements Serializable {

    private String result;

    public FightResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }


}
