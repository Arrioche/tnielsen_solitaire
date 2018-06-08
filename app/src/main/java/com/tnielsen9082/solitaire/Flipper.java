package com.tnielsen9082.solitaire;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Flipper {
    String[] correspond= new String[52];
    int[] fronts={ R.drawable.acespade, R.drawable.twospade,R.drawable.threespade, R.drawable.fourspade,R.drawable.fivespade,R.drawable.sixspade,R.drawable.sevenspade,R.drawable.eightspade,R.drawable.ninespade,R.drawable.tenspade,R.drawable.jackspade,R.drawable.queenspade,R.drawable.kingspade,
            R.drawable.acediamond, R.drawable.twodiamond,R.drawable.threediamond, R.drawable.fourdiamond,R.drawable.fivediamond,R.drawable.sixdiamond,R.drawable.sevendiamond,R.drawable.eightdiamond,R.drawable.ninediamond,R.drawable.tendiamond,R.drawable.jackdiamond,R.drawable.queendiamond,R.drawable.kingdiamond,
            R.drawable.aceclub, R.drawable.twoclub,R.drawable.threeclub, R.drawable.fourclub,R.drawable.fiveclub,R.drawable.sixclub,R.drawable.sevenclub,R.drawable.eightclub,R.drawable.nineclub,R.drawable.tenclub,R.drawable.jackclub,R.drawable.queenclub,R.drawable.kingclub,
            R.drawable.aceheart, R.drawable.twoheart,R.drawable.threeheart, R.drawable.fourheart,R.drawable.fiveheart,R.drawable.sixheart,R.drawable.sevenheart,R.drawable.eightheart,R.drawable.nineheart,R.drawable.tenheart,R.drawable.jackheart,R.drawable.queenheart,R.drawable.kingheart};

    int[] cardHalves={ R.drawable.acespadehalf, R.drawable.twospadehalf,R.drawable.threespadehalf, R.drawable.fourspadehalf,R.drawable.fivespadehalf,R.drawable.sixspadehalf,R.drawable.sevenspadehalf,R.drawable.eightspadehalf,R.drawable.ninespadehalf,R.drawable.tenspadehalf,R.drawable.jackspadehalf,R.drawable.queenspadehalf,R.drawable.kingspadehalf,
            R.drawable.acediamondhalf, R.drawable.twodiamondhalf,R.drawable.threediamondhalf, R.drawable.fourdiamondhalf,R.drawable.fivediamondhalf,R.drawable.sixdiamondhalf,R.drawable.sevendiamondhalf,R.drawable.eightdiamondhalf,R.drawable.ninediamondhalf,R.drawable.tendiamondhalf,R.drawable.jackdiamondhalf,R.drawable.queendiamondhalf,R.drawable.kingdiamondhalf,
            R.drawable.aceclubhalf, R.drawable.twoclubhalf,R.drawable.threeclubhalf, R.drawable.fourclubhalf,R.drawable.fiveclubhalf,R.drawable.sixclubhalf,R.drawable.sevenclubhalf,R.drawable.eightclubhalf,R.drawable.nineclubhalf,R.drawable.tenclubhalf,R.drawable.jackclubhalf,R.drawable.queenclubhalf,R.drawable.kingclubhalf,
            R.drawable.acehearthalf, R.drawable.twohearthalf,R.drawable.threehearthalf, R.drawable.fourhearthalf,R.drawable.fivehearthalf,R.drawable.sixhearthalf,R.drawable.sevenhearthalf,R.drawable.eighthearthalf,R.drawable.ninehearthalf,R.drawable.tenhearthalf,R.drawable.jackhearthalf,R.drawable.queenhearthalf,R.drawable.kinghearthalf};
    int[] goals = {R.drawable.heartfiller,R.drawable.clubfiller,R.drawable.spadefiller,R.drawable.diamondfiller};
    void init(){
        int counter=0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 14; j++) {
                correspond[counter]=""+(i*100+j);
                counter++;
            }
        }
    }
}
