package com.example.assignmenttwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignmenttwo.model.GameInfo;

import org.w3c.dom.Text;

/**
 * CustomAdapter Class
 *
 * This class is used when the listView is created. It is a adapter design pattern.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    //Variables
    Context context;
    String[] text;
    private GameInfo gameInfo;

    /**
     * CustomAdapter Constructor
     *
     * @param context is the activity context
     * @param cardTextData is the data used for the textView
     */
    public CustomAdapter(Context context, String[] cardTextData) {
        super(context, R.layout.row_listview, cardTextData);
        this.context = context;
        this.text = cardTextData;
    }

    /**
     * getView Method
     *
     * @param pos is the position of the item within the adapter's data set of the item whose view we want
     * @param convertView is the old view to reuse
     * @param parent is parent of the view that will we will attach to
     * @return is the view that is returns
     */
    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Getting the only 1 instance of the class
        gameInfo = GameInfo.getInstance();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_listview, parent, false);

        //The old views to reuse
        ImageView image = (ImageView) convertView.findViewById(R.id.iv_face_down);
        TextView cardInfo = (TextView) convertView.findViewById(R.id.tv_card_text);

        //Getting the current position card in the list
        Card card = gameInfo.drawableList.get(pos);

        //Checking whether the card is disabled
        if(card.getDisabled()) {
            image.setImageResource(card.getFaceUp());
        } else {
            image.setImageResource(card.getFaceDown());
        }

        //Setting the textView text
        cardInfo.setText(text[card.getPosition()]);
        return convertView;
    }
}
