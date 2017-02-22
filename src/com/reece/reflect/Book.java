package com.reece.reflect;

import java.io.Serializable;

/**
 * Created by mi on 17-2-22.
 */
public class Book implements Serializable{
    private boolean isEBook = false;
    private float prize = 0;
    private int extra;

    public Book() {

    }

    public Book(boolean isEBook, float prize) {
        this(isEBook, prize, 1000);
    }

    private Book(boolean isEBook, float prize, int extra) {
        this.extra = extra;
        this.isEBook = isEBook;
        this.prize = prize;
    }

    private void setPrize(float prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "isEBook="+isEBook+",prize="+prize+",extra="+extra;
    }
}
