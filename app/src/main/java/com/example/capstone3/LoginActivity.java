package com.example.capstone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.capstone3.MainActivity;
import com.example.capstone3.SignUpActivity;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button LoginButton = findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), MainActivity.class);
                        startActivity(intent);
            }
        });
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        Button SignUpButton = findViewById(R.id.SignUpButton);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        Button FirebaseButton = findViewById(R.id.FirebaseButton);
        FirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),locker.class);
                startActivity(intent);
            }
        });
    }
}
