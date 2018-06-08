package com.tnielsen9082.solitaire;

import android.content.ClipData;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public final class MyTouchListener implements View.OnTouchListener {
    //the action that the class performs
    private LinearLayout h;
    private LinearLayout d;
    private LinearLayout s;
    private LinearLayout c;
    private LinearLayout discard;
    public void id(View tag2,View tag3,View tag4,View tag5, View tag6){
        c=(LinearLayout)tag2;
        h=(LinearLayout)tag3;
        s=(LinearLayout)tag4;
        d=(LinearLayout)tag5;
        discard=(LinearLayout)tag6;
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //makes the class motionEvent perform the getAction function
        //motionEvents track what is touching the screen
        //ACTION_DOWN means that someone just started touching it
        //so this if is checking if the square has been tapped
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //the layout that the image being touched is currently in
            LinearLayout parent = (LinearLayout)view.getParent();
            //makes a clipData
            //since we are just using pictures there is no data
            //but there could be data
            //this is how you copy/paste things
            int coattails = parent.getChildCount()-parent.indexOfChild(view)-1;
            ClipData data;
            if(coattails<10){
                data = ClipData.newPlainText("", view.getContentDescription()+"0"+coattails);
            }
            else{
                data = ClipData.newPlainText("", view.getContentDescription()+""+coattails);
            }
            //make a custom drag shadow
            //once I create it
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            //you can't take cards from the four objective piles and also the discard
            if(parent!=c&&parent!=d&&parent!=h&&parent!=s&&parent!=discard&&view.getContentDescription().length()==3){
                //if the view is not in any of the four objective piles
                view.startDrag(data, shadowBuilder, view, 1);
                Log.e("TAG",""+data);
            }
            /*view.setVisibility(View.INVISIBLE);
            for (int i = 0; i < coattails; i++) {
                parent.getChildAt(parent.getChildCount()-i-1).setVisibility(View.INVISIBLE);
            }*/
            //so, the cards are all in the moving container, where they will be unpacked by the drag listener
            //the clip data is the top card, so the rules of solitaire still work
            //we don't need to clean the array since we're going to create a new one
            return true;
        } else {
            return false;
        }
    }
}
