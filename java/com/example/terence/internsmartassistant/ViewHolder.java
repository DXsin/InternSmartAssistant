package com.example.terence.internsmartassistant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Terence on 10/5/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView message,timeIn,timeOut;

    public ViewHolder(View itemView){
        super(itemView);

        message = (TextView) itemView.findViewById(R.id.message);
        timeIn = (TextView) itemView.findViewById(R.id.timeIn);
        timeOut = (TextView) itemView.findViewById(R.id.timeOut);
    }
}
