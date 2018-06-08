package com.tnielsen9082.solitaire;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    private static final String TAG = "MainActivity";
    ArrayList <Card> pile1 = new ArrayList<>();
    ArrayList <Card> pile2 = new ArrayList<>();
    ArrayList <Card> pile3 = new ArrayList<>();
    ArrayList <Card> pile4 = new ArrayList<>();
    ArrayList <Card> pile5 = new ArrayList<>();
    ArrayList <Card> pile6 = new ArrayList<>();
    ArrayList <Card> pile7 = new ArrayList<>();
    ArrayList <Card> hpile = new ArrayList<>();
    ArrayList <Card> spile = new ArrayList<>();
    ArrayList <Card> cpile = new ArrayList<>();
    ArrayList <Card> dpile = new ArrayList<>();
    ArrayList <View> cards = new ArrayList<>();
    //the lists of piles that are dealt from
    ArrayList <View> cardsReset = new ArrayList<>();
    ArrayList <LinearLayout> cardPiles = new ArrayList<>();
    //the arrays for the card faces
    String[] halfCorrespond = new String[52];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solitaire_layout);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#228B22"));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //adds all the card faces to their array
        int[] fronts= { R.drawable.acespade, R.drawable.twospade,R.drawable.threespade, R.drawable.fourspade,R.drawable.fivespade,R.drawable.sixspade,R.drawable.sevenspade,R.drawable.eightspade,R.drawable.ninespade,R.drawable.tenspade,R.drawable.jackspade,R.drawable.queenspade,R.drawable.kingspade,
                R.drawable.acediamond, R.drawable.twodiamond,R.drawable.threediamond, R.drawable.fourdiamond,R.drawable.fivediamond,R.drawable.sixdiamond,R.drawable.sevendiamond,R.drawable.eightdiamond,R.drawable.ninediamond,R.drawable.tendiamond,R.drawable.jackdiamond,R.drawable.queendiamond,R.drawable.kingdiamond,
                R.drawable.aceclub, R.drawable.twoclub,R.drawable.threeclub, R.drawable.fourclub,R.drawable.fiveclub,R.drawable.sixclub,R.drawable.sevenclub,R.drawable.eightclub,R.drawable.nineclub,R.drawable.tenclub,R.drawable.jackclub,R.drawable.queenclub,R.drawable.kingclub,
                R.drawable.aceheart, R.drawable.twoheart,R.drawable.threeheart, R.drawable.fourheart,R.drawable.fiveheart,R.drawable.sixheart,R.drawable.sevenheart,R.drawable.eightheart,R.drawable.nineheart,R.drawable.tenheart,R.drawable.jackheart,R.drawable.queenheart,R.drawable.kingheart};
        //adds the card halves to their array
        int[] halves= { R.drawable.acespadehalf, R.drawable.twospadehalf,R.drawable.threespadehalf, R.drawable.fourspadehalf,R.drawable.fivespadehalf,R.drawable.sixspadehalf,R.drawable.sevenspadehalf,R.drawable.eightspadehalf,R.drawable.ninespadehalf,R.drawable.tenspadehalf,R.drawable.jackspadehalf,R.drawable.queenspadehalf,R.drawable.kingspadehalf,
                R.drawable.acediamondhalf, R.drawable.twodiamondhalf,R.drawable.threediamondhalf, R.drawable.fourdiamondhalf,R.drawable.fivediamondhalf,R.drawable.sixdiamondhalf,R.drawable.sevendiamondhalf,R.drawable.eightdiamondhalf,R.drawable.ninediamondhalf,R.drawable.tendiamondhalf,R.drawable.jackdiamondhalf,R.drawable.queendiamondhalf,R.drawable.kingdiamondhalf,
                R.drawable.aceclubhalf, R.drawable.twoclubhalf,R.drawable.threeclubhalf, R.drawable.fourclubhalf,R.drawable.fiveclubhalf,R.drawable.sixclubhalf,R.drawable.sevenclubhalf,R.drawable.eightclubhalf,R.drawable.nineclubhalf,R.drawable.tenclubhalf,R.drawable.jackclubhalf,R.drawable.queenclubhalf,R.drawable.kingclubhalf,
                R.drawable.acehearthalf, R.drawable.twohearthalf,R.drawable.threehearthalf, R.drawable.fourhearthalf,R.drawable.fivehearthalf,R.drawable.sixhearthalf,R.drawable.sevenhearthalf,R.drawable.eighthearthalf,R.drawable.ninehearthalf,R.drawable.tenhearthalf,R.drawable.jackhearthalf,R.drawable.queenhearthalf,R.drawable.kinghearthalf};

        //creates the array that controls what images are drawn from their array
        int counter=0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 14; j++) {
                halfCorrespond[counter]=""+(i*100+j);
                counter++;
            }
        }
        //create the touch listener to pass to the drag listeners
        MyTouchListener touch = new MyTouchListener();
        touch.id(findViewById(R.id.heartLayout),findViewById(R.id.clubLayout),findViewById(R.id.spadeLayout),findViewById(R.id.diaLayout),findViewById(R.id.deckDiscard));
        //creates the arrays for dealing the cards
        //adds them also to the cardHalf array
        //spade
        cards.add(findViewById(R.id.spadeAce));
        cards.add(findViewById(R.id.spadeTwo));
        cards.add(findViewById(R.id.spadeThree));
        cards.add(findViewById(R.id.spadeFour));
        cards.add(findViewById(R.id.spadeFive));
        cards.add(findViewById(R.id.spadeSix));
        cards.add(findViewById(R.id.spadeSeven));
        cards.add(findViewById(R.id.spadeEight));
        cards.add(findViewById(R.id.spadeNine));
        cards.add(findViewById(R.id.spadeTen));
        cards.add(findViewById(R.id.spadeJack));
        cards.add(findViewById(R.id.spadeQueen));
        cards.add(findViewById(R.id.spadeKing));
        //half
        //diamond
        cards.add(findViewById(R.id.diamondAce));
        cards.add(findViewById(R.id.diamondTwo));
        cards.add(findViewById(R.id.diamondThree));
        cards.add(findViewById(R.id.diamondFour));
        cards.add(findViewById(R.id.diamondFive));
        cards.add(findViewById(R.id.diamondSix));
        cards.add(findViewById(R.id.diamondSeven));
        cards.add(findViewById(R.id.diamondEight));
        cards.add(findViewById(R.id.diamondNine));
        cards.add(findViewById(R.id.diamondTen));
        cards.add(findViewById(R.id.diamondJack));
        cards.add(findViewById(R.id.diamondQueen));
        cards.add(findViewById(R.id.diamondKing));
        //club
        cards.add(findViewById(R.id.clubAce));
        cards.add(findViewById(R.id.clubTwo));
        cards.add(findViewById(R.id.clubThree));
        cards.add(findViewById(R.id.clubFour));
        cards.add(findViewById(R.id.clubFive));
        cards.add(findViewById(R.id.clubSix));
        cards.add(findViewById(R.id.clubSeven));
        cards.add(findViewById(R.id.clubEight));
        cards.add(findViewById(R.id.clubNine));
        cards.add(findViewById(R.id.clubTen));
        cards.add(findViewById(R.id.clubJack));
        cards.add(findViewById(R.id.clubQueen));
        cards.add(findViewById(R.id.clubKing));
        //heart
        cards.add(findViewById(R.id.heartAce));
        cards.add(findViewById(R.id.heartTwo));
        cards.add(findViewById(R.id.heartThree));
        cards.add(findViewById(R.id.heartFour));
        cards.add(findViewById(R.id.heartFive));
        cards.add(findViewById(R.id.heartSix));
        cards.add(findViewById(R.id.heartSeven));
        cards.add(findViewById(R.id.heartEight));
        cards.add(findViewById(R.id.heartNine));
        cards.add(findViewById(R.id.heartTen));
        cards.add(findViewById(R.id.heartJack));
        cards.add(findViewById(R.id.heartQueen));
        cards.add(findViewById(R.id.heartKing));
        //creates the array of piles to be dealt to
        cardPiles.add((LinearLayout)findViewById(R.id.one));
        cardPiles.add((LinearLayout)findViewById(R.id.two));
        cardPiles.add((LinearLayout)findViewById(R.id.three));
        cardPiles.add((LinearLayout)findViewById(R.id.four));
        cardPiles.add((LinearLayout)findViewById(R.id.five));
        cardPiles.add((LinearLayout)findViewById(R.id.six));
        cardPiles.add((LinearLayout)findViewById(R.id.seven));
        //creates each card's touchListener
        /*1=spade
        2=Diamond
        3=Club
        4=Heart
        odd is black and even is red*/
        //sets each of the cards to be able to be picked up
        findViewById(R.id.spadeAce).setOnTouchListener(touch);
        findViewById(R.id.diamondAce).setOnTouchListener(touch);
        findViewById(R.id.clubAce).setOnTouchListener(touch);
        findViewById(R.id.heartAce).setOnTouchListener(touch);

        findViewById(R.id.spadeTwo).setOnTouchListener(touch);
        findViewById(R.id.clubTwo).setOnTouchListener(touch);
        findViewById(R.id.heartTwo).setOnTouchListener(touch);
        findViewById(R.id.diamondTwo).setOnTouchListener(touch);

        findViewById(R.id.spadeThree).setOnTouchListener(touch);
        findViewById(R.id.clubThree).setOnTouchListener(touch);
        findViewById(R.id.heartThree).setOnTouchListener(touch);
        findViewById(R.id.diamondThree).setOnTouchListener(touch);

        findViewById(R.id.spadeFive).setOnTouchListener(touch);
        findViewById(R.id.clubFive).setOnTouchListener(touch);
        findViewById(R.id.heartFive).setOnTouchListener(touch);
        findViewById(R.id.diamondFive).setOnTouchListener(touch);

        findViewById(R.id.spadeFour).setOnTouchListener(touch);
        findViewById(R.id.clubFour).setOnTouchListener(touch);
        findViewById(R.id.heartFour).setOnTouchListener(touch);
        findViewById(R.id.diamondFour).setOnTouchListener(touch);

        findViewById(R.id.spadeSix).setOnTouchListener(touch);
        findViewById(R.id.clubSix).setOnTouchListener(touch);
        findViewById(R.id.heartSix).setOnTouchListener(touch);
        findViewById(R.id.diamondSix).setOnTouchListener(touch);

        findViewById(R.id.spadeSeven).setOnTouchListener(touch);
        findViewById(R.id.clubSeven).setOnTouchListener(touch);
        findViewById(R.id.heartSeven).setOnTouchListener(touch);
        findViewById(R.id.diamondSeven).setOnTouchListener(touch);

        findViewById(R.id.spadeEight).setOnTouchListener(touch);
        findViewById(R.id.clubEight).setOnTouchListener(touch);
        findViewById(R.id.heartEight).setOnTouchListener(touch);
        findViewById(R.id.diamondEight).setOnTouchListener(touch);

        findViewById(R.id.spadeNine).setOnTouchListener(touch);
        findViewById(R.id.clubNine).setOnTouchListener(touch);
        findViewById(R.id.heartNine).setOnTouchListener(touch);
        findViewById(R.id.diamondNine).setOnTouchListener(touch);

        findViewById(R.id.spadeTen).setOnTouchListener(touch);
        findViewById(R.id.clubTen).setOnTouchListener(touch);
        findViewById(R.id.heartTen).setOnTouchListener(touch);
        findViewById(R.id.diamondTen).setOnTouchListener(touch);

        findViewById(R.id.spadeJack).setOnTouchListener(touch);
        findViewById(R.id.clubJack).setOnTouchListener(touch);
        findViewById(R.id.heartJack).setOnTouchListener(touch);
        findViewById(R.id.diamondJack).setOnTouchListener(touch);

        findViewById(R.id.spadeQueen).setOnTouchListener(touch);
        findViewById(R.id.clubQueen).setOnTouchListener(touch);
        findViewById(R.id.heartQueen).setOnTouchListener(touch);
        findViewById(R.id.diamondQueen).setOnTouchListener(touch);

        findViewById(R.id.spadeKing).setOnTouchListener(touch);
        findViewById(R.id.clubKing).setOnTouchListener(touch);
        findViewById(R.id.heartKing).setOnTouchListener(touch);
        findViewById(R.id.diamondKing).setOnTouchListener(touch);
        //sets each of the big piles to be able to be put down in
        MyDragListener pileOne=new MyDragListener();
        Button reset = findViewById(R.id.reset);
        pileOne.id(pile1, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));

        MyDragListener pileTwo=new MyDragListener();
        pileTwo.id(pile2, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));

        MyDragListener pileThree=new MyDragListener();
        pileThree.id(pile3, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));

        MyDragListener pileFour=new MyDragListener();
        pileFour.id(pile4, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));

        MyDragListener pileFive=new MyDragListener();
        pileFive.id(pile5, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));

        MyDragListener pileSix=new MyDragListener();
        pileSix.id(pile6, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));

        MyDragListener pileSeven=new MyDragListener();
        pileSeven.id(pile7, (LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard));
        Button deckButton = findViewById(R.id.deck);
        //creates the drag listeners for the goal piles
        PileDragListener spadePile = new PileDragListener();
        spadePile.id(spile,(LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard),(LinearLayout)findViewById(R.id.winScreen),1, deckButton,reset);
        spadePile.winPiles((LinearLayout)findViewById(R.id.heartLayout),(LinearLayout)findViewById(R.id.clubLayout),(LinearLayout)findViewById(R.id.spadeLayout),(LinearLayout)findViewById(R.id.diaLayout),(LinearLayout)findViewById(R.id.one),(LinearLayout)findViewById(R.id.two),(LinearLayout)findViewById(R.id.three),(LinearLayout)findViewById(R.id.four),(LinearLayout)findViewById(R.id.five),(LinearLayout)findViewById(R.id.six),(LinearLayout)findViewById(R.id.seven),(ImageView)findViewById(R.id.heart),(ImageView)findViewById(R.id.club),(ImageView)findViewById(R.id.spade),(ImageView)findViewById(R.id.diamond));

        PileDragListener clubPile = new PileDragListener();
        clubPile.id(cpile,(LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard),(LinearLayout)findViewById(R.id.winScreen),3, deckButton,reset);
        clubPile.winPiles((LinearLayout)findViewById(R.id.heartLayout),(LinearLayout)findViewById(R.id.clubLayout),(LinearLayout)findViewById(R.id.spadeLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.one),(LinearLayout)findViewById(R.id.two),(LinearLayout)findViewById(R.id.three),(LinearLayout)findViewById(R.id.four),(LinearLayout)findViewById(R.id.five),(LinearLayout)findViewById(R.id.six),(LinearLayout)findViewById(R.id.seven),(ImageView)findViewById(R.id.heart),(ImageView)findViewById(R.id.club),(ImageView)findViewById(R.id.spade),(ImageView)findViewById(R.id.diamond));

        PileDragListener diaPile = new PileDragListener();
        diaPile.id(dpile,(LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard),(LinearLayout)findViewById(R.id.winScreen),2, deckButton,reset);
        diaPile.winPiles((LinearLayout)findViewById(R.id.heartLayout),(LinearLayout)findViewById(R.id.clubLayout),(LinearLayout)findViewById(R.id.spadeLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.one),(LinearLayout)findViewById(R.id.two),(LinearLayout)findViewById(R.id.three),(LinearLayout)findViewById(R.id.four),(LinearLayout)findViewById(R.id.five),(LinearLayout)findViewById(R.id.six),(LinearLayout)findViewById(R.id.seven),(ImageView)findViewById(R.id.heart),(ImageView)findViewById(R.id.club),(ImageView)findViewById(R.id.spade),(ImageView)findViewById(R.id.diamond));

        PileDragListener heartPile = new PileDragListener();
        heartPile.id(hpile,(LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard),(LinearLayout)findViewById(R.id.winScreen),4, deckButton,reset);
        heartPile.winPiles((LinearLayout)findViewById(R.id.heartLayout),(LinearLayout)findViewById(R.id.clubLayout),(LinearLayout)findViewById(R.id.spadeLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.one),(LinearLayout)findViewById(R.id.two),(LinearLayout)findViewById(R.id.three),(LinearLayout)findViewById(R.id.four),(LinearLayout)findViewById(R.id.five),(LinearLayout)findViewById(R.id.six),(LinearLayout)findViewById(R.id.seven),(ImageView)findViewById(R.id.heart),(ImageView)findViewById(R.id.club),(ImageView)findViewById(R.id.spade),(ImageView)findViewById(R.id.diamond));

        //assignes the dragListeners to each pile
        //and the layouts
        findViewById(R.id.one).setOnDragListener(pileOne);
        findViewById(R.id.two).setOnDragListener(pileTwo);
        findViewById(R.id.three).setOnDragListener(pileThree);
        findViewById(R.id.four).setOnDragListener(pileFour);
        findViewById(R.id.five).setOnDragListener(pileFive);
        findViewById(R.id.six).setOnDragListener(pileSix);
        findViewById(R.id.seven).setOnDragListener(pileSeven);
        findViewById(R.id.heartLayout).setOnDragListener(heartPile);
        findViewById(R.id.clubLayout).setOnDragListener(clubPile);
        findViewById(R.id.diaLayout).setOnDragListener(diaPile);
        findViewById(R.id.spadeLayout).setOnDragListener(spadePile);
        //activates the button
        deckButton.setBackgroundResource(R.drawable.cardback);
        myClickListener listener = new myClickListener();
        resetClickListener listener2 = new resetClickListener();
        myWinListener listener3 = new myWinListener();
        deckButton.setOnClickListener(listener);
        reset.setOnClickListener(listener2);
        Button winButton = findViewById(R.id.winButton);
        winButton.setOnClickListener(listener3);
        winButton.setEnabled(false);
        listener.id((LinearLayout)findViewById(R.id.deckLayout),(LinearLayout)findViewById(R.id.deckShow),(LinearLayout)findViewById(R.id.deckDiscard),deckButton);
        listener2.id(cards,cardPiles, cardsReset, (LinearLayout)findViewById(R.id.deckLayout),deckButton);
        listener3.id(cards,cardPiles, cardsReset, (LinearLayout)findViewById(R.id.deckLayout),deckButton,reset,(ImageView)findViewById(R.id.heart),(ImageView)findViewById(R.id.club),(ImageView)findViewById(R.id.spade),(ImageView)findViewById(R.id.diamond));
        //deals the cards
        deckDeal(cards, cardPiles, cardsReset);
    }
    @SuppressLint("WrongConstant")
    public void deckDeal(ArrayList deal, ArrayList piles, ArrayList reset){
        Random rand =new Random();
        for (int i = 6; i >=0; i--) {
            for (int j = 0; j <= i; j++) {
                //gets a random card from deal
                View card = (View)deal.get(rand.nextInt(deal.size()));
                //takes the card out of deal
                deal.remove(card);
                reset.add(card);
                //puts the card in the card pile
                ((LinearLayout)card.getParent()).removeView(card);
                ((LinearLayout)piles.get(i)).addView(card);
                //shows the card (cards start as gone)
                card.setVisibility(View.VISIBLE);
                //add some code here to turn the card facedown once that is built
                if (j!=i){
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