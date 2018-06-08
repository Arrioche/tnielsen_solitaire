package com.tnielsen9082.solitaire;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

//this is the class that the receptacles get
public final class MyDragListener implements View.OnDragListener {
    private CharSequence clip;
    private static final String TAG = "MainActivity";
    private ArrayList<Card> value=new ArrayList<>();
    private LinearLayout deck;
    private LinearLayout display;
    private LinearLayout discard;
    private Flipper myFlipper= new Flipper();
    public void id(ArrayList<Card> tag, LinearLayout tag2, LinearLayout tag3, LinearLayout tag4){
        value = tag;
        deck= tag2;
        display =tag3;
        discard=tag4;
        myFlipper.init();
    }
    @Override
    //when a drag is started this activates
    public boolean onDrag(View v, DragEvent event) {
        //calls getAction on the drag event
        //which tells you if it has:
            /*started
              entered the box
              left the box
              dropped
              still in the box
              or force quit
             */
        //we don't actually use this though
        //int action = event.getAction();
        //a new Java syntax appeared!
        //it looks for the action like I said
        //and then performs whichever case matches that action
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                //check the clearing()
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
                if(!value.isEmpty()) {
                    //if the suit is the same color and the number is one LESS
                    Log.e(TAG, "card suit: "+clip.charAt(0));
                    Log.e(TAG, "pile suit: "+value.get(value.size()-1).getSuit());
                    if (value.get(value.size()-1).getNumber() == ((clip.charAt(1)-48)*10+clip.charAt(2) - 47) && value.get(value.size()-1).getSuit()%2 != (clip.charAt(0) % 2)) {
                        ViewGroup owner = (ViewGroup) view.getParent();
                        //takes the view from the group it was in
                        owner.removeView(view);
                        //sets the receptacle to a layout that can accept new data
                        LinearLayout container = (LinearLayout) v;
                        //turn the last card in the pile into a half
                        ImageView halver = (ImageView)((LinearLayout) v).getChildAt(((LinearLayout) v).getChildCount()-1);
                        for (int i = 0; i < 52; i++) {
                            if(halver.getContentDescription().equals(myFlipper.correspond[i])){
                                (halver).setImageResource(myFlipper.cardHalves[i]);
                                i=52;
                            }
                        }
                        //adds it to the new group
                        container.addView(view);
                        Card card = new Card((clip.charAt(0)-48),(clip.charAt(1)-48)*10+clip.charAt(2) - 48);
                        value.add(card);
                        ArrayList<View> cards = new ArrayList<>();
                        for (int i = (clip.charAt(3)-48)*10+clip.charAt(4) - 48; i > 0; i--) {
                            cards.add(owner.getChildAt(owner.getChildCount()-i));
                        }
                        for (int i = 0; i < (clip.charAt(3) - 48) * 10 + clip.charAt(4) - 48; i++) {
                            owner.removeView(cards.get(i));
                            container.addView(cards.get(i));
                            card = new Card(cards.get(i).getContentDescription().charAt(0)-48,(cards.get(i).getContentDescription().charAt(1)-48)*10+cards.get(i).getContentDescription().charAt(2) - 48);
                            value.add(card);
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
                        //fixes the cards drawn from the deck
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
                //if the card is a king
                else if (((clip.charAt(1)-48)*10+clip.charAt(2)-48==13)){
                    ViewGroup owner = (ViewGroup) view.getParent();
                    //takes the view from the group it was in
                    owner.removeView(view);
                    //sets the receptacle to a layout that can accept new data
                    LinearLayout container = (LinearLayout) v;
                    ImageView halver = (ImageView)((LinearLayout) v).getChildAt(((LinearLayout) v).getChildCount()-1);
                    for (int i = 0; i < 52; i++) {
                        if(halver.getContentDescription().equals(myFlipper.correspond[i])){
                            (halver).setImageResource(myFlipper.cardHalves[i]);
                            i=52;
                        }
                    }
                    //adds it to the new group
                    container.addView(view);
                    //and to the new array
                    Card card = new Card((clip.charAt(0)-48),(clip.charAt(1)-48)*10+clip.charAt(2) - 48);
                    value.add(card);
                    ArrayList<View> cards = new ArrayList<>();
                    //makes an array of all the cards being brought along with the card being clicked on
                    for (int i = (clip.charAt(3)-48)*10+clip.charAt(4) - 48; i > 0; i--) {
                        cards.add(owner.getChildAt(owner.getChildCount()-i));
                    }
                    //puts every card from that array into the new array/layout
                    for (int i = 0; i < (clip.charAt(3) - 48) * 10 + clip.charAt(4) - 48; i++) {
                        owner.removeView(cards.get(i));
                        container.addView(cards.get(i));
                        card = new Card(cards.get(i).getContentDescription().charAt(0)-48,(cards.get(i).getContentDescription().charAt(1)-48)*10+cards.get(i).getContentDescription().charAt(2) - 48);
                        value.add(card);
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
                    //deals with what happens if the card comes from the deck
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
        for (int i = 1; i < ((LinearLayout) v).getChildCount(); i++) {
            Card clean = new Card((((LinearLayout) v).getChildAt(i).getContentDescription() + "").charAt(0) - 48,((((LinearLayout) v).getChildAt(i).getContentDescription() + "").charAt(1) - 48) * 10 + (((LinearLayout) v).getChildAt(i).getContentDescription() + "").charAt(2) - 48);
            value.add(clean);
        }
    }
}
