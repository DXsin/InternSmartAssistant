package com.example.terence.internsmartassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button Journal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
       // button =(Button)findViewById(R.id.button);

        Journal = (Button) findViewById(R.id.Journal);
        Journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), JournalMain.class);
                view.getContext().startActivity(Intent);}
        });
    }



}
