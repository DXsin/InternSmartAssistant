package com.example.terence.internsmartassistant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Terence on 9/23/2017.
 */

public class JournalMain extends AppCompatActivity {
    //private JournalDB db;
    // private ListView rMessages;
    private Button Add;
    private  RecyclerView rv;
    private List<JournalModel> jourList;
    private RecyclerAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference journalRef;

    //Firebasehelper helper;
  //  DatabaseReference journalRef;
  //  RecyclerAdapter adapter;
   // List<JournalModel> jourList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_main);

        jourList = new ArrayList<>();
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance();
        journalRef =database.getReference();

        LinearLayoutManager lis = new LinearLayoutManager(this);
        rv.setLayoutManager(lis);
       // createList();
        journalRef = FirebaseDatabase.getInstance().getReference();
        adapter = new RecyclerAdapter(jourList);
        rv.setAdapter(adapter);

        updateList();

        Add = (Button) findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), JournalEntry.class);
                view.getContext().startActivity(Intent);
            }
        });


    }
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 0:
                removeJour(item.getGroupId());
                break;
            case 1:
                changeJour(item.getGroupId());
                break;

        }
        return super.onContextItemSelected(item);
    }

//    private void createResult(){
//        for(int i = 0; i<10;i++){
//            jourList.add(new JournalModel("message","in","out","key"));
//        }
//        //return jourList;
//    }
//    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//        }
//    }
    private void createList(){
       // List<JournalModel> mode = new ArrayList<>();
        for(int i= 0;i<10;i++){
            jourList.add(new JournalModel("message","in","out"));
        }
    }
    private void updateList() {
        journalRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                jourList.add(dataSnapshot.getValue(JournalModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                JournalModel model = dataSnapshot.getValue(JournalModel.class);
                int index = getItemIndex(model);
                jourList.set(index,model);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                JournalModel model = dataSnapshot.getValue(JournalModel.class);
                int index = getItemIndex(model);
                jourList.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

        private int getItemIndex(JournalModel model){
            int index = -1;
            for(int i=0;i<jourList.size();i++){
                if(jourList.get(i).key.equals(model.key)){
                    index = i;
                    break;
                }
        }
            return index;
    }
    private void removeJour(int position){
        journalRef.child(jourList.get(position).key).removeValue();

    }
    private void changeJour(int position){
        JournalModel model = jourList.get(position);
        Map<String,Object> jourValue = model.toMap();
        Map<String,Object> newJour = new HashMap<>();

        newJour.put(model.key,jourValue);

        journalRef.updateChildren(newJour);

    }

}







