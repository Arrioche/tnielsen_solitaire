package com.tnielsen9082.solitaire;

import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class resetClickListener implements View.OnClickListener {
    private ArrayList deal;
    private static final String TAG = "MainActivity";
    private ArrayList piles;
    private ArrayList reset;
    private LinearLayout deck;
    private Button button;
    private Flipper myFlipper= new Flipper();
    public void id (ArrayList tag, ArrayList tag2, ArrayList tag3, LinearLayout tag4, Button tag5){
        //the deck, where the cards are held at first
        deal = tag;
        //the list of seven piles
        piles = tag2;
        //the reset array
        reset =tag3;
        //the beginning deck array
        deck = tag4;
        //the button for the deck
        button= tag5;
        myFlipper.init();
    }
    @Override
    public void onClick(View view) {
        button.setBackgroundResource(R.drawable.cardback);
        Random rand =new Random();
        for (int i = 0; i < deal.size(); i++) {
            View card = (View)deal.get(i);
            ((LinearLayout)card.getParent()).removeView(card);
            deck.addView(card);
            card.getLayoutParams().width = button.getLayoutParams().width;
            card.setVisibility(View.GONE);
            if(card.getContentDescription().length()>3){
                card.setContentDescription((card.getContentDescription()+"").substring(0,3));
            }
        }
        for (int i = 0; i < deal.size(); i++) {
            for (int j = 0; j < 52; j++) {
                View card=(View)deal.get(i);
                if(card.getContentDescription().equals(myFlipper.correspond[j])){
                    ((ImageView)card).setImageResource(myFlipper.fronts[j]);
                    j=52;
                }
            }
        }
        for (int i = 6; i >-1; i--) {
            for (int j = 0; j < i+1; j++) {
                //gets a random card from deal
                View card = (View)deal.get(rand.nextInt(deal.size()));
                //takes the card out of deal
                reset.add(card);
                deal.remove(card);
                //puts the card in the card pile
                ((LinearLayout)card.getParent()).removeView(card);
                ((LinearLayout)piles.get(i)).addView(card);
                //shows the card (cards start as gone)
                card.setVisibility(View.VISIBLE);
                //add some code here to turn the card facedown once that is built
                if (j<i){
                    //add a touch listener to the card at the bottom of each pile
                    card.setContentDescription(card.getContentDescription()+"X");
                    ((ImageView)card).setImageResource(R.drawable.cardbackhalf);
                }

            }
        }
        Collections.shuffle(deal);
        for (int k = 0; k < deal.size(); k++) {
            LinearLayout parent = (LinearLayout)((View)deal.get(k)).getParent();
            //takes each card from the shuffled deal and puts it in the layout in the new order
            parent.removeView((View)deal.get(k));
            parent.addView((View)deal.get(k));

        }
        int resetSize = reset.size();
        for (int k = 0; k < resetSize; k++) {
            //adds reset back to deal
            deal.add(reset.get(0));
            reset.remove(0);
        }
    }
}

