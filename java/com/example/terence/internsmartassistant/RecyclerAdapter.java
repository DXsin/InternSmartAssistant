package com.example.terence.internsmartassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terence on 10/3/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    //Context context;
    private List<JournalModel> list;

   public RecyclerAdapter(List<JournalModel>list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false));
//        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      JournalModel journalModel = list.get(position);

        holder.message.setText(journalModel.message);
        holder.timeIn.setText(journalModel.in);
        holder.timeOut.setText(journalModel.out);

//        holder.message.setText(jourList.get(position));
//        holder.timeIn.setText(jourList.get(position));
//        holder.timeOut.setText(jourList.get(position));
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(),0,0,"Delete");
                menu.add(holder.getAdapterPosition(),0,1,"Update");

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
         TextView message;
         TextView timeIn;
         TextView timeOut;

      public ViewHolder(View itemView) {
            super(itemView);
          message = (TextView) itemView.findViewById(R.id.message);
          timeIn = (TextView) itemView.findViewById(R.id.timeIn);
          timeOut = (TextView) itemView.findViewById(R.id.timeOut);


        }
}}



