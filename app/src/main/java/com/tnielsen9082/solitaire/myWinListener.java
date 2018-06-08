package com.tnielsen9082.solitaire;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class myWinListener implements View.OnClickListener {
    private ArrayList deal;
    private static final String TAG = "MainActivity";
    private ArrayList piles;
    private ArrayList reset;
    private LinearLayout deck;
    private Button button;
    private Button resetButton;
    private Flipper myFlipper = new Flipper();
    private ImageView[] goalBacks=new ImageView[4];
    public void id (ArrayList tag, ArrayList tag2, ArrayList tag3, LinearLayout tag4, Button tag5, Button tag6,ImageView e, ImageView f, ImageView g, ImageView h){
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
        //the reset button
        resetButton = tag6;
        myFlipper.init();
        goalBacks[0]=e;
        goalBacks[1]=f;
        goalBacks[2]=g;
        goalBacks[3]=h;
    }
    @Override
    public void onClick(View view) {
        //turns off the reset button
        view.setEnabled(false);
        //sets the four piles to their default state
        goalBacks[0].setImageResource(myFlipper.goals[0]);
        goalBacks[1].setImageResource(myFlipper.goals[1]);
        goalBacks[2].setImageResource(myFlipper.goals[2]);
        goalBacks[3].setImageResource(myFlipper.goals[3]);
        //hides the win screen
        for (int i = 0; i < ((LinearLayout) view.getParent()).getChildCount(); i++) {
            ((LinearLayout) view.getParent()).getChildAt(i).setVisibility(View.GONE);
        }
        //resets the deck button to look full
        button.setBackgroundResource(R.drawable.cardback);
        button.setVisibility(View.VISIBLE);
        //returns the reset button
        resetButton.setEnabled(true);
        resetButton.setVisibility(View.VISIBLE);
        Random rand =new Random();
        //puts every card back in the deck
        //fixes its size
        //makes it able to be picked up
        //and makes it invisible
        for (int i = 0; i < deal.size(); i++) {
            View card = (View)deal.get(i);
            card.getLayoutParams().width = button.getLayoutParams().width;
            ((LinearLayout)card.getParent()).removeView(card);
            deck.addView(card);
            card.setVisibility(View.GONE);
            if(card.getContentDescription().length()==4){
                card.setContentDescription((card.getContentDescription()+"").substring(0,card.getContentDescription().length() - 1));
            }
        }
        //sets every card to its face
        for (int i = 0; i < deal.size(); i++) {
            for (int j = 0; j < 52; j++) {
                View card=(View)deal.get(i);
                if(card.getContentDescription().equals(myFlipper.correspond[j])){
                    ((ImageView)card).setImageResource(myFlipper.fronts[j]);
                    j=52;
                }
            }
        }
        //deals the cards
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
        //shuffles the deck
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
