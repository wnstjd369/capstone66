package com.example.capstone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.capstone2.activity.EnterPinActivity;
import com.google.firebase.database.FirebaseDatabase;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent23 = new Intent(getApplicationContext(), EnterPinActivity.class);
        startActivity((intent23));
        setContentView(R.layout.activity_admin);

        Button button20 = findViewById(R.id.button20);
        Button button21 = findViewById(R.id.button21);
        Button button22 = findViewById(R.id.button22);

        button20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), locker.class);
                startActivity(intent1);


            }
        });
        button21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), locker.class);
                startActivity(intent2);


            }
        });
        button22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), locker.class);
                startActivity(intent3);


            }
        });
    }
}
