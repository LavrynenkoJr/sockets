package com.company.messageModels;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by Cyborg on 3/27/2017.
 */
public class Massage implements Serializable {

    private int fromClientId;
    private int toClientId;
    private int hit;
    private int hp;

    public Massage(int fromClientId, int toClientId, int hit, int hp) {
        this.fromClientId = fromClientId;
        this.toClientId = toClientId;
        this.hit = hit;
        this.hp = hp;
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }


    public int getFromClientId() {
        return fromClientId;
    }

    public void setFromClientId(int fromClientId) {
        this.fromClientId = fromClientId;
    }

    public int getToClientId() {
        return toClientId;
    }

    public void setToClientId(int toClientId) {
        this.toClientId = toClientId;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
