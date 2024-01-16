package com.example.assignmenttwo.model;

import com.example.assignmenttwo.Card;
import com.example.assignmenttwo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * GameInfo Class
 *
 * This class has all the game information. It is a singleton design pattern.
 */
public class GameInfo {

    //Variables and Arrays
    public ArrayList<Card> drawableList = new ArrayList<Card>();
    public Card firstPick;
    public Card secondPick;

    //Singleton support
    private static GameInfo instance;

    /**
     * GameInfo Constructor
     */
    private GameInfo() {

    }

    /**
     * getInstance Method
     *
     * This is used to get the single instance of the game info class
     * @return is the instance it returns
     */
    public static GameInfo getInstance() {
        if(instance == null) {
            instance = new GameInfo();
        }
        return instance;
    }

    /**
     * generateCardList Method
     *
     * This is used to generate a random list of cards and setting their card positions for the Card Class variable
     */
    public void generateCardList() {
        //Generating 12 cards
        for(int i = 0; i < 2; i++) {
            drawableList.add(new Card(R.drawable.img_card_1));
            drawableList.add(new Card(R.drawable.img_card_2));
            drawableList.add(new Card(R.drawable.img_card_3));
            drawableList.add(new Card(R.drawable.img_card_4));
            drawableList.add(new Card(R.drawable.img_card_5));
            drawableList.add(new Card(R.drawable.img_card_6));
        }
        Collections.shuffle(drawableList);

        //Setting each individual card position for the card class
        for(int j = 0; j < drawableList.size(); j++) {
            drawableList.get(j).setPosition(j);
        }
    }

    /**
     * resetPicks Method
     *
     * This method is used to reset the first and second pick variables when they are both not null
     */
    public void resetPicks() {
        if(firstPick != null && secondPick != null) {
            firstPick = null;
            secondPick = null;
        }
    }
}
