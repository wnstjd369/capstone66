package com.example.capstone3;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.capstone3.ManagementActivity;
import com.example.capstone3.MenuActivity;
import com.example.capstone3.R;
import com.example.capstone3.subActivity;
import com.firebase.ui.storage.images.FirebaseImageLoader;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    private StorageReference vStorageRef;

    private DatabaseReference mDatabaseref;
    private DatabaseReference uDatabaseref;


    private String uploadId;
    private String uploadId2;
    private String fileLink;
    private String date;
    private ImageView imageView;
    private String urii;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LOG2","2");

        uploadId = getIntent().getStringExtra("Name"); //부모이름
        uploadId2 = getIntent().getStringExtra("PName"); //자식이름

        Time now = new Time(Time.getCurrentTimezone());
        now.setToNow();
        final String date = now.format("%y%m%d");

        mStorageRef = FirebaseStorage.getInstance().getReference();
        vStorageRef = mStorageRef.child("image/"+uploadId2+"/"+date+".jpg");

        uDatabaseref = FirebaseDatabase.getInstance().getReference("users");


        imageView = findViewById(R.id.imageView);
        if (vStorageRef != null) {
            vStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    urii = uri.toString();
                   /*
                    Glide.with(getApplicationContext())
                            .load(vStorageRef)
                            .into(imageView);
                    */
                    Picasso.with(
                            getApplicationContext()).
                            load(urii).
                            fit().
                            centerInside().
                            into(imageView);

                }
            });
        }


        Log.d("주소",vStorageRef.toString());


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






      //  if (user != null) {
      /*      uploadId = getIntent().getStringExtra("Name");
            uploadId2 = getIntent().getStringExtra("PName");
            if (uploadId2 != null) {
                mDatabaseref = FirebaseDatabase.getInstance().getReference("image").child(uploadId2);
                mDatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ImageUpload imageUpload = dataSnapshot.getValue(ImageUpload.class);

                        vStorageRef =  mStorageRef.child("image/"+)
                        if (dataSnapshot.child(uploadId2).exists()) {
                            fileLink = imageUpload.getUrl();
                            Log.d("fileLink", fileLink);
                            Uri uri = Uri.parse(fileLink);
                            Log.d("uri", uri.toString());

                        /*
                         ImageView draweeView = findViewById(R.id.imageView);
                         draweeView.setImageURI(uri);
                        */
                          //  urii = uri;
                     //   }
                //    }
/*
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }
        /*} else {
            signInAnonymously();
        }*/
        /*
        if (urii != null) {
            Glide.with(this)
                    .load(urii)
                    .into(imageView);
        }
        */
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                uDatabaseref.child(uploadId2).child("Lock").setValue("1");
                Toast.makeText(getApplicationContext(), "LOCK ON", Toast.LENGTH_LONG).show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                uDatabaseref.child(uploadId2).child("Lock").setValue("0");
                Toast.makeText(getApplicationContext(), "LOCK OFF", Toast.LENGTH_LONG).show();
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
