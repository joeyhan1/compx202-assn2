package com.example.assignmenttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.assignmenttwo.model.GameInfo;

/**
 * MainActivity Class
 *
 * This class is used when the application is launched
 */
public class MainActivity extends AppCompatActivity {

    //Variables
    private GameInfo gameInfo;

    /**
     * onCreate Method
     *
     * This method is used to create everything that was in the main activity layout
     * @param savedInstanceState used to restore to a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the only 1 instance of the class
        gameInfo = GameInfo.getInstance();

        //Finding the button id
        Button buttonPressed = (Button) findViewById(R.id.b_start);
        /**
         * onClick Method
         *
         * This method is used to generate the card list and go to the list activity after button is clicked
         * @param view used to find out which view was clicked
         */
        buttonPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Shows that the click function is working
//                Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
                gameInfo.generateCardList();
                //Testing for the card list and whether it's random
                //Toast.makeText(getApplicationContext(), gameInfo.drawableList.get(11), Toast.LENGTH_LONG).show();
                //Used to go to ListActivity Class (Explicit Intent)
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }
}