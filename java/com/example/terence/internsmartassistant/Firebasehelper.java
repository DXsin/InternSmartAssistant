package com.example.terence.internsmartassistant;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Terence on 10/5/2017.
 */

public class Firebasehelper {
    DatabaseReference db;
    Boolean saved=null;
    ArrayList<JournalModel> modelList = new ArrayList<>();

    public Firebasehelper(DatabaseReference db){
        this.db = db;
    }

    public Boolean save(JournalModel model){
        if(model==null){
            saved=false;
        }
        else{
            try{
                db.child("JournalModel").push().setValue(model);
                saved=true;
            }
            catch(DatabaseException e){
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot){
        modelList.clear();
        for(DataSnapshot ds: dataSnapshot.getChildren()){
//            String message = ds.getValue(JournalModel.class).getMessage();
//            String in = ds.getValue(JournalModel.class).getIn();
//            String out = ds.getValue(JournalModel.class).getOut();
            JournalModel model = ds.getValue(JournalModel.class);
            modelList.add(model);


        }
    }

    public ArrayList<JournalModel> retrieve(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return modelList;
    }
}
