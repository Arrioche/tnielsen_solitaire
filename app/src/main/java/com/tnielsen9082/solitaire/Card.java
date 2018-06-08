package com.tnielsen9082.solitaire;

import android.widget.ImageView;

public class Card{
    private int suit;
    private int number;
    private ImageView face;
    public ImageView getFace() {
        return face;
    }

    public void setFace(ImageView face) {
        this.face = face;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public Card(int suit, int number){
        //this.face=face;
        this.number=number;
        this.suit=suit;
    }


}
