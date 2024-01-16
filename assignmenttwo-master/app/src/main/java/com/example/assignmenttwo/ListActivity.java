package com.example.assignmenttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttwo.model.GameInfo;

import org.w3c.dom.Text;

import java.util.List;

/**
 * ListActivity Class
 *
 * This class is used when the ListActivity is launched
 */
public class ListActivity extends AppCompatActivity {

    //Variables and Arrays
    private GameInfo gameInfo;
    private ListView listView;
    String[] cardTextData = {"Card 1", "Card 2", "Card 3", "Card 4", "Card 5", "Card 6", "Card 7",
            "Card 8", "Card 9", "Card 10", "Card 11", "Card 12"};
    int pickCardNum = 1;

    /**
     * onCreate Method
     *
     * This method is used to create everything that was in the list activity layout
     * @param savedInstanceState used to restore to a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Getting the only 1 instance of the class
        gameInfo = GameInfo.getInstance();

        //Update Text View
        TextView textView = (TextView) findViewById(R.id.tv_pick);
        textView.setText(getString(R.string.first_pick));

        //Update List View
        listView = (ListView) findViewById(R.id.lv_card_list);
        CustomAdapter adapterCustom = new CustomAdapter(this, cardTextData);
        listView.setAdapter(adapterCustom);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * onItemClick Method
             *
             * This method is used to go to the card activity after button is pressed
             * @param parent is the adapter view (listView)
             * @param view is the view that is clicked
             * @param pos is the position of the view that was clicked
             * @param id is the id of the view
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                //Shows that the click function is working
               //Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
                //Testing purposes
                Log.d("POS", String.valueOf(pos));

                //Checking if the clicked position is disabled
                if(gameInfo.drawableList.get(pos).getDisabled()) {
                    return;
                }
                Intent cardIntent = new Intent(getApplicationContext(), CardActivity.class);
                //Sending extra information to the card activity to use
                cardIntent.putExtra("cardPosition", pos);
                cardIntent.putExtra("cardPickNum", pickCardNum);

                //Checking if the second pick is the same position as the first
                if(gameInfo.firstPick != null && pos == gameInfo.firstPick.getPosition()) {
                    Log.d("POS", "Same as first pick position");
                    return;
                }

                //Checking for the card pick number
                if(pickCardNum == 1) {
                    textView.setText(getString(R.string.second_pick));
                    pickCardNum++;
                } else if(pickCardNum == 2) {
                    textView.setText(getString(R.string.first_pick));
                    pickCardNum--;
                }

                //Going to Card Activity
                startActivity(cardIntent);
            }
        });
    }

    /**
     * onResume Method
     *
     * This method is used to refresh the listView every time the activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        disable();
        CustomAdapter adapterCustom = new CustomAdapter(this, cardTextData);
        listView.setAdapter(adapterCustom);

        //Refreshing the listView
        listView.refreshDrawableState();
    }

    /**
     * disable Method
     *
     * This method is used to disable matched cards
     */
    private void disable() {
        //Checking if firstPick and secondPick is not empty, checking if their face up image are the same and checking if they are not the same position
        if(gameInfo.firstPick != null && gameInfo.secondPick != null && gameInfo.firstPick.getFaceUp() == gameInfo.secondPick.getFaceUp() && gameInfo.firstPick.getPosition() != gameInfo.secondPick.getPosition()) {
            //Testing purposes
            Log.d("POS", "Disable");

            //Disabling the click function for both the first and second pick item
            gameInfo.firstPick.setDisabled(true);
            gameInfo.secondPick.setDisabled(true);
        }

        //Resets the first pick and second pick variables
        gameInfo.resetPicks();
    }
}