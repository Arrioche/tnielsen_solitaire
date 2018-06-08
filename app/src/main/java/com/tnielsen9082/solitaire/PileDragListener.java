package com.tnielsen9082.solitaire;

import android.content.ClipData;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public final class PileDragListener implements View.OnDragListener {
    private CharSequence clip;
    private static final String TAG = "MainActivity";
    private ArrayList<Card> value=new ArrayList<>();
    private LinearLayout deck;
    private LinearLayout display;
    private LinearLayout discard;
    private int suit;
    private LinearLayout A;
    private LinearLayout B;
    private LinearLayout C;
    private LinearLayout D;
    private LinearLayout[] piles = new LinearLayout[7];
    private LinearLayout winLayout;
    private Flipper myFlipper= new Flipper();
    private Button deckButton;
    private Button resetButton;
    private ImageView[] goalBacks= new ImageView[4];
    public void id(ArrayList<Card> tag, LinearLayout tag2, LinearLayout tag3, LinearLayout tag4, LinearLayout tag5, int tag6, Button tag7, Button tag8){
        value = tag;
        deck= tag2;
        display =tag3;
        discard=tag4;
        winLayout = tag5;
        myFlipper.init();
        suit= tag6;
        deckButton = tag7;
        resetButton = tag8;
    }
    void winPiles(LinearLayout a, LinearLayout b, LinearLayout c, LinearLayout d, LinearLayout One, LinearLayout Two, LinearLayout Three, LinearLayout Four, LinearLayout Five, LinearLayout Six, LinearLayout Seven, ImageView e, ImageView f, ImageView g, ImageView h){
        A =a;
        B=b;
        C=c;
        D=d;
        piles[0] = One;
        piles[1]= Two;
        piles[2] = Three;
        piles[3]= Four;
        piles[4]= Five;
        piles[5]= Six;
        piles[6]= Seven;
        goalBacks[0]=e;
        goalBacks[1]=f;
        goalBacks[2]=g;
        goalBacks[3]=h;

    }
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                clearing(v);
                //stop the code
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //change the look to show it is droppable in
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //change the look back
                break;
            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);
                // Gets the text data from the item.
                clip = item.getText();
                clip=clip.toString();
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                //what's a viewGroup?
                //who knows?
                //it's a layout thing
                if(value.isEmpty()) {
                    //if there are no coattails
                    //and the suit is correct
                    //and the number is 1
                    if((clip.charAt(clip.length()-2) - 48) * 10 + clip.charAt(clip.length()-1) - 48==0&&(clip.charAt(1)-48)*10+clip.charAt(2)-48==1&&clip.charAt(0)-48==suit){
                        ViewGroup owner = (ViewGroup) view.getParent();
                        //takes the view from the group it was in
                        owner.removeView(view);
                        //sets the receptacle to a layout that can accept new data
                        LinearLayout container = (LinearLayout) v;
                        //adds it to the new group
                        container.addView(view);
                        Card card = new Card((clip.charAt(0)-48),(clip.charAt(1)-48)*10+clip.charAt(2) - 48);
                        value.add(card);
                        //turns the card to a full view
                        for (int i = 0; i < 52; i++) {
                            if(view.getContentDescription().equals(myFlipper.correspond[i])){
                                ((ImageView)view).setImageResource(myFlipper.fronts[i]);
                                i=52;
                            }
                        }
                        //turns the next card faceup
                        if(owner.getChildCount()!=0){
                            View turn = owner.getChildAt(owner.getChildCount()-1);
                            if(turn.getContentDescription().length()==4){
                                turn.setContentDescription((turn.getContentDescription()+"").substring(0, turn.getContentDescription().length() - 1));
                            }
                            for (int i = 0; i < 52; i++) {
                                if(turn.getContentDescription().equals(myFlipper.correspond[i])){
                                    ((ImageView)turn).setImageResource(myFlipper.fronts[i]);
                                    i=52;
                                }
                            }
                        }
                        if(owner==display&&discard.getChildCount()!=0){
                            //takes the last card put in the discard and puts it back in the display
                            View refresh = discard.getChildAt(discard.getChildCount()-1);
                            discard.removeView(refresh);
                            display.addView(refresh);
                            refresh.getLayoutParams().width = display.getLayoutParams().width;
                            refresh.setVisibility(View.VISIBLE);
                            if(discard.getChildCount()>1) {
                                discard.getChildAt(discard.getChildCount() - 2).setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
                //(clip.charAt(0)-48)== value.get(value.size()-1).getSuit()&&
                //if there are no coattails
                //and the suit is correct
                //and the number is one more that the previous one in the array
                else if ((clip.charAt(3)-48) * 10 + clip.charAt(4) - 48==0&&(clip.charAt(0)-48)== value.get(value.size()-1).getSuit()&&((clip.charAt(1)-48)*10+clip.charAt(2) - 48)==value.get(value.size()-1).getNumber()+1){
                    ViewGroup owner = (ViewGroup) view.getParent();
                        //takes the view from the group it was in
                        owner.removeView(view);
                        //sets the receptacle to a layout that can accept new data
                        LinearLayout container = (LinearLayout) v;
                        //adds it to the new group
                        container.addView(view);
                        Card card = new Card((clip.charAt(0)-48),(clip.charAt(1)-48)*10+clip.charAt(2) - 48);
                        value.add(card);
                        //turns the card to a full view
                        for (int i = 0; i < 52; i++) {
                            if(view.getContentDescription().equals(myFlipper.correspond[i])){
                             ((ImageView)view).setImageResource(myFlipper.fronts[i]);
                             i=52;
                            }
                         }
                        ((LinearLayout) v).getChildAt(((LinearLayout) v).getChildCount()-2).setVisibility(View.GONE);
                        //turns the next card faceup
                        if(owner.getChildCount()!=0){
                            View turn = owner.getChildAt(owner.getChildCount()-1);
                            if(turn.getContentDescription().length()==4){
                                turn.setContentDescription((turn.getContentDescription()+"").substring(0, turn.getContentDescription().length() - 1));
                            }
                            for (int i = 0; i < 52; i++) {
                                if(turn.getContentDescription().equals(myFlipper.correspond[i])){
                                    ((ImageView)turn).setImageResource(myFlipper.fronts[i]);
                                    i=52;
                                }
                            }
                        }
                    if(owner==display&&discard.getChildCount()!=0){
                        //takes the last card put in the discard and puts it back in the display
                        View refresh = discard.getChildAt(discard.getChildCount()-1);
                        discard.removeView(refresh);
                        display.addView(refresh);
                        refresh.getLayoutParams().width = display.getLayoutParams().width;
                        refresh.setVisibility(View.VISIBLE);
                        if(discard.getChildCount()>1) {
                            discard.getChildAt(discard.getChildCount() - 2).setVisibility(View.VISIBLE);
                        }
                    }
                }

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                View test = (View) event.getLocalState();
                if(!winChecker(A,B,C,D)) {
                    test.setVisibility(View.VISIBLE);
                }
                //makes the view show up again
            default:
                //stops if there is no more dragging
                break;
        }
        //this allows you to receive updates on the status of the dragged thing
        return true;
    }
    private void clearing(View v) {
        //cleans out the stack
        while(!value.isEmpty()){
            value.remove(0);
        }
        //adds the cards from the layout to the stack
        for (int i = 0; i < ((LinearLayout) v).getChildCount(); i++) {
            Card clean = new Card((((LinearLayout) v).getChildAt(i).getContentDescription() + "").charAt(0) - 48,((((LinearLayout) v).getChildAt(i).getContentDescription() + "").charAt(1) - 48) * 10 + (((LinearLayout) v).getChildAt(i).getContentDescription() + "").charAt(2) - 48);
            value.add(clean);
        }
    }
    private boolean winChecker(LinearLayout a, LinearLayout b, LinearLayout c, LinearLayout d){
        if(a.getChildCount()==13&&b.getChildCount()==13&&c.getChildCount()==13&&d.getChildCount()==13){
            for (int i = 0; i < winLayout.getChildCount(); i++) {
                winLayout.getChildAt(i).setVisibility(View.VISIBLE);
            }
            (winLayout.getChildAt(1)).setEnabled(true);
            for (int i = 0; i < A.getChildCount(); i++) {
                A.getChildAt(i).setVisibility(View.GONE);
            }
            goalBacks[0].setImageResource(myFlipper.fronts[51]);
            for (int i = 0; i < D.getChildCount(); i++) {
                D.getChildAt(i).setVisibility(View.GONE);
            }
            goalBacks[3].setImageResource(myFlipper.fronts[25]);
            for (int i = 0; i < C.getChildCount(); i++) {
                C.getChildAt(i).setVisibility(View.GONE);
            }
            goalBacks[2].setImageResource(myFlipper.fronts[12]);
            for (int i = 0; i < B.getChildCount(); i++) {
                B.getChildAt(i).setVisibility(View.GONE);
            }
            deckButton.setVisibility(View.GONE);
            resetButton.setEnabled(false);
            resetButton.setVisibility(View.GONE);
            return true;
        }
        else{
            int counter =0;
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < piles[i].getChildCount(); j++) {
                    if(piles[i].getChildAt(j).getContentDescription().length()==4){
                        counter++;
                    }
                }
            }
            if (counter==0){
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < piles[i].getChildCount(); j++) {
                        View transfer = piles[i].getChildAt(j);
                        transfer.setVisibility(View.GONE);
                    }
                }
                for (int i = 0; i < discard.getChildCount(); i++) {
                    discard.getChildAt(i).setVisibility(View.GONE);
                }
                for (int i = 0; i < display.getChildCount(); i++) {
                    display.getChildAt(i).setVisibility(View.GONE);
                }
                for (int i = 0; i < A.getChildCount(); i++) {
                    A.getChildAt(i).setVisibility(View.GONE);
                }
                goalBacks[0].setImageResource(myFlipper.fronts[51]);
                for (int i = 0; i < D.getChildCount(); i++) {
                    D.getChildAt(i).setVisibility(View.GONE);
                }
                goalBacks[3].setImageResource(myFlipper.fronts[25]);
                for (int i = 0; i < C.getChildCount(); i++) {
                    C.getChildAt(i).setVisibility(View.GONE);
                }
                goalBacks[2].setImageResource(myFlipper.fronts[12]);
                for (int i = 0; i < B.getChildCount(); i++) {
                    B.getChildAt(i).setVisibility(View.GONE);
                }
                goalBacks[1].setImageResource(myFlipper.fronts[38]);
                for (int i = 0; i < winLayout.getChildCount(); i++) {
                    winLayout.getChildAt(i).setVisibility(View.VISIBLE);
                }
                (winLayout.getChildAt(1)).setEnabled(true);
                deckButton.setVisibility(View.GONE);
                resetButton.setEnabled(false);
                resetButton.setVisibility(View.GONE);
                return true;
            }
        }
        return false;
    }

}
