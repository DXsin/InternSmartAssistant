package com.example.terence.internsmartassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Terence on 10/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    ArrayList<JournalModel> modelList;

    public MyAdapter(Context context,ArrayList<JournalModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.message.setText(modelList.get(position).getMessage());
        holder.timeIn.setText(modelList.get(position).getIn());
        holder.timeOut.setText(modelList.get(position).getOut());
    }
    @Override
    public int getItemCount(){
        return modelList.size();
    }
}
