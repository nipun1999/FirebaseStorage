package com.itparkbynipun.firebaseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class imageActivity extends AppCompatActivity {

    ImageView imageView;
    DatabaseReference mdatabase;
    private String url;
    private Button uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.imageView);
        uploadImage = findViewById(R.id.addImageBtn);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("images").child("url");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                url = dataSnapshot.getValue().toString();
////                Toast.makeText(imageActivity.this, url, Toast.LENGTH_SHORT).show();
                Glide.with(imageActivity.this)
                        .load(url)
                        .into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addimage = new Intent(imageActivity.this,addImage.class);
                startActivity(addimage);
            }
        });




    }
}
