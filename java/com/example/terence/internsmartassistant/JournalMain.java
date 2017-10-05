package com.example.terence.internsmartassistant;

import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    RecyclerView rv;
    //private List<JournalModel> jourList;
//    private RecyclerAdapter adapter;
//    private FirebaseDatabase database;
//    private DatabaseReference journalRef;
    DatabaseReference db;
    Firebasehelper helper;
    MyAdapter adapter;
    EditText editMessage,editIn,editOut;
    Button button;

    //Firebasehelper helper;
  //  DatabaseReference journalRef;
  //  RecyclerAdapter adapter;
   // List<JournalModel> jourList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_main);

        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseDatabase.getInstance().getReference();
        helper=new Firebasehelper(db);

        adapter = new MyAdapter(this,helper.retrieve());
        rv.setAdapter(adapter);




        Add = (Button) findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent Intent = new Intent(view.getContext(), JournalEntry.class);
//                view.getContext().startActivity(Intent);
                displayInputDialog();

            }
        });


    }
    private void displayInputDialog()
    {
        Dialog d  = new Dialog(this);
        d.setTitle("New Entry");
        d.setContentView(R.layout.journal_entry);
        editMessage = (EditText)d.findViewById(R.id.editMessage);
        editIn = (EditText)d.findViewById(R.id.editIn);
        editOut = (EditText)d.findViewById(R.id.editOut);

        Button button = (Button)d.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String message = editMessage.getText().toString();
            String in = editIn.getText().toString();
            String out = editOut.getText().toString();

            JournalModel model = new JournalModel();
            model.setMessage(message);
            model.setIn(in);
            model.setOut(out);

            if(message != null && message.length()>0 ){
                if(helper.save(model)){
                    editMessage.setText("");
                    editIn.setText("");
                    editOut.setText("");
                    adapter = new MyAdapter(JournalMain.this,helper.retrieve());
                    rv.setAdapter(adapter);
                }
            }
            else
            {
                Toast.makeText(JournalMain.this,"Fields required!", Toast.LENGTH_LONG).show();

            }

        }

    });
        d.show();
    }


}







