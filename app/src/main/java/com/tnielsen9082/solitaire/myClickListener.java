package com.tnielsen9082.solitaire;

import android.graphics.Color;
import android.nfc.Tag;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.security.PrivateKey;
public class myClickListener implements View.OnClickListener {
    private LinearLayout deck;
    private static final String TAG = "MainActivity";
    private LinearLayout display;
    private LinearLayout discard;
    private Button button;
    public void id (LinearLayout tag, LinearLayout tag2, LinearLayout tag3, Button tag4){
        //the deck, where the cards are held at first
        deck = tag;
        //the display, where you can pull cards from
        display = tag2;
        //the discard, where cards show up from if you take the card in the display
        discard =tag3;
        button = tag4;
    }
    @Override
    public void onClick(View view) {
        //takes the current card in the display and puts it into the discard
        if(display.getChildCount()!=0&&deck.getChildCount()!=0) {
            View card2 = display.getChildAt(0);
            display.removeView(card2);
            card2.getLayoutParams().width = 120;
            discard.addView(card2);
            card2.setVisibility(View.VISIBLE);
        }
        //takes the top card of the deck and puts it in the display
        if(deck.getChildCount()!=0) {
            View card = deck.getChildAt(0);
            deck.removeView(card);
            display.addView(card);
            card.getLayoutParams().width = display.getLayoutParams().width;
            card.setVisibility(View.VISIBLE);
            if(deck.getChildCount()==0){
                button.setBackgroundResource(R.drawable.buttonreset);
            }
        }
        else{
            button.setBackgroundResource(R.drawable.cardback);
            //takes the cards from the discard and puts them back in the deck
            while(discard.getChildCount()!=0) {
                View card = discard.getChildAt(0);
                discard.removeView(card);
                deck.addView(card);
                card.getLayoutParams().width = display.getLayoutParams().width;
                card.setVisibility(View.GONE);
            }
            //puts the display card back in the deck
            if(display.getChildCount()!=0) {
                View card2 = display.getChildAt(0);
                display.removeView(card2);
                deck.addView(card2);
                card2.setVisibility(View.GONE);
            }
        }
        for (int i = 0; i < discard.getChildCount(); i++) {
            if(i<discard.getChildCount()-2){
                discard.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }
}
