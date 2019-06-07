package com.example.capstone3;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.capstone3.ManagementActivity;
import com.example.capstone3.MenuActivity;
import com.example.capstone3.R;
import com.example.capstone3.subActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference mDatabaseref;
    private String uploadId;
    private String fileLink;
    private ImageView imageView;
    private Uri urii;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference("users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */

        imageView = findViewById(R.id.imageView);
        if (user != null) {
            uploadId = getIntent().getStringExtra("Name");
            if (uploadId != null) {
                mDatabaseref = FirebaseDatabase.getInstance().getReference("image").child(uploadId);
                mDatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ImageUpload imageUpload = dataSnapshot.getValue(ImageUpload.class);
                        fileLink = imageUpload.getUrl();
                        Log.d("fileLink", fileLink);
                        Uri uri = Uri.parse(fileLink);
                        Log.d("uri",uri.toString());

                        /*
                         ImageView draweeView = findViewById(R.id.imageView);
                         draweeView.setImageURI(uri);
                        */
                        urii = uri;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }
        } else {
            signInAnonymously();
        }

        Glide.with(this)
                .load(urii)
                .into(imageView);


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
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("FAIL", "signInAnonymously:FAILURE", exception);
                    }
                });
    }

}
