package com.example.assignmenttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttwo.model.GameInfo;

/**
 * CardActivity Class
 *
 * This class is used when the CardActivity is launched
 */
public class CardActivity extends AppCompatActivity {

    //Variable
    private GameInfo gameInfo;

    /**
     * onCreate Method
     *
     * This method is used to create everything that was in the card activity layout
     * @param savedInstanceState used to restore to a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //Getting the only 1 instance of the class
        gameInfo = GameInfo.getInstance();

        //Getting the intent from ListActivity
        Intent intent = getIntent();

        //Getting the extra information from ListActivity
        int imagePosition = intent.getIntExtra("cardPosition", 0);
        int cardPick = intent.getIntExtra("cardPickNum", 0);

        //The face up image of the current clicked item
        int drawableID = gameInfo.drawableList.get(imagePosition).getFaceUp();

        //Testing purposes
//        String message = String.valueOf(cardPick);
//        Toast.makeText(getApplicationContext(),  message, Toast.LENGTH_SHORT).show();

        //Getting the image view and setting the image
        ImageView imageView = findViewById(R.id.iv_selected_card);
        imageView.setImageResource(drawableID);

        //Getting the text view
        TextView textView = findViewById(R.id.tv_pick_information);

        //Checking the card pick and putting the card pick information into the game info variables
        if(cardPick == 1) {
            textView.setText(getString(R.string.first_pick_card));
            gameInfo.firstPick = gameInfo.drawableList.get(imagePosition);
        } else if(cardPick == 2 && drawableID == gameInfo.firstPick.getFaceUp()) {
            textView.setText(getString(R.string.second_pick_match));
            gameInfo.secondPick = gameInfo.drawableList.get(imagePosition);
        } else if(cardPick == 2 && drawableID != gameInfo.firstPick.getFaceUp()) {
            textView.setText(getString(R.string.second_pick_unmatch));
            gameInfo.secondPick = gameInfo.drawableList.get(imagePosition);
        }
    }
}