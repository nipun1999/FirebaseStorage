package com.itparkbynipun.firebaseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView nameTxt;
    EditText nameEdtxt;
    Button addbtn;
    DatabaseReference nameDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = findViewById(R.id.nameTxt);
        nameEdtxt = findViewById(R.id.NameEdtText);
        addbtn = findViewById(R.id.addBtn);
        nameDatabase = FirebaseDatabase.getInstance().getReference();

        //pulling the data
        nameDatabase.child("Names").push().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name")
                        .getValue()
                        .toString();
                nameTxt.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                 Log.e("Classname",databaseError.toString());
            }
        });

        //pushing the data
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdtxt.getText().toString();
                nameDatabase.child("Names").push().child("name").setValue(name);
                Log.i("name",name);
                Toast.makeText(MainActivity.this, "Name added Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });



    }
}
