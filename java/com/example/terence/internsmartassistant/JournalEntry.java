package com.example.terence.internsmartassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Terence on 9/29/2017.
 */

public class JournalEntry extends AppCompatActivity implements View.OnClickListener {
    private EditText editMessage;
    private EditText editIn;
    private EditText editOut;
    private Button button;
    private DatabaseReference journalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_entry);

        editMessage = (EditText) findViewById(R.id.editMessage);
        editIn = (EditText) findViewById(R.id.editIn);
        editOut = (EditText) findViewById(R.id.editOut);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        //artists = new ArrayList<>();
        journalRef = FirebaseDatabase.getInstance().getReference();
    }
    private void saveJour() {

        String message = editMessage.getText().toString().trim();
        String in = editIn.getText().toString().trim();
        String out = editOut.getText().toString().trim();

        String id = journalRef.push().getKey();


        JournalModel jour = new JournalModel(message, in, out);
        journalRef.child(id).setValue(jour);

        Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
    }


    public void onClick(View v){
        if(v == button){
            saveJour();
        }
    }
}
