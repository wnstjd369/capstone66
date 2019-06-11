package com.example.capstone3;

import android.content.Intent;
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
    private DatabaseReference uDatabaseref;
    private String uploadId;
    private String uploadId2;
    private String fileLink;
    private ImageView imageView;
    private Uri urii;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        Log.d("LOG2","2");
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
            uploadId2 = getIntent().getStringExtra("PName");
            if (uploadId != null) {
                mDatabaseref = FirebaseDatabase.getInstance().getReference("image").child(uploadId);
                mDatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ImageUpload imageUpload = dataSnapshot.getValue(ImageUpload.class);
                        if (dataSnapshot.child(uploadId).exists()) {
                            fileLink = imageUpload.getUrl();
                            Log.d("fileLink", fileLink);
                            Uri uri = Uri.parse(fileLink);
                            Log.d("uri", uri.toString());

                        /*
                         ImageView draweeView = findViewById(R.id.imageView);
                         draweeView.setImageURI(uri);
                        */
                            urii = uri;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }
        } else {
            signInAnonymously();
        }

        if (urii != null) {
            Glide.with(this)
                    .load(urii)
                    .into(imageView);
        }

        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                uDatabaseref = FirebaseDatabase.getInstance().getReference("users").child(uploadId2);
                uDatabaseref.child("Lock").setValue("1");
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                uDatabaseref = FirebaseDatabase.getInstance().getReference("users").child(uploadId2);
                uDatabaseref.child("Lock").setValue("0");

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
