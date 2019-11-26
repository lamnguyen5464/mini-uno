package com.example.lforestor.superminiuno;

import java.io.Serializable;

/**
 * Created by LAM on 6/22/2017.
 */

public class card implements Serializable {
    private String name;
    private int pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public card(String name, int pic) {

        this.name = name;
        this.pic = pic;
    }
}
