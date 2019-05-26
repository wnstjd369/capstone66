package com.example.capstone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.capstone3.ManagementActivity;
import com.example.capstone3.MenuActivity;
import com.example.capstone3.R;
import com.example.capstone3.subActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManagementActivity.class);
                intent.putExtra("name","학생관리");
                startActivity(intent);

            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("name","미션관리");
                startActivity(intent);


            }
        });
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), subActivity.class);
                intent.putExtra("name","용돈관리");
                startActivity(intent);


            }
        });
    }
}
