package com.example.assignmenttwo;

import android.content.Intent;

import com.example.assignmenttwo.model.GameInfo;

/**
 * Card Class
 *
 * This class has all the information of a single card
 */
public class Card {

    //Variables
    private GameInfo gameInfo;
    private int cardPosition = 0;
    private int faceUpImageID = 0;
    private int faceDownImageID = 0;
    private boolean disabled = false;

    /**
     * Card Constructor
     *
     * @param imageID is the id of the image
     */
    public Card(int imageID) {
        faceUpImageID = imageID;
        faceDownImageID = R.drawable.img_card_facedown;
    }

    //Getters for the private variables
    public int getPosition() {
        return cardPosition;
    }

    public int getFaceUp() {
        return faceUpImageID;
    }

    public int getFaceDown() {
        return faceDownImageID;
    }

    public boolean getDisabled() {
        return disabled;
    }

    //Setters for the private variables
    public void setPosition(int position) {
        this.cardPosition = position;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
