package com.itparkbynipun.firebaseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class viewDetails extends AppCompatActivity {


    private Realm mrealm;
    private EditText title;
    private TextView message;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        title = findViewById(R.id.titleEditText);
        message = findViewById(R.id.messageTxtView);
        search = findViewById(R.id.searchBtn);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("details.realm").schemaVersion(0).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mrealm = Realm.getInstance(realmConfiguration);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titlevalue = title.getText().toString();
                RealmResults<details> messagevalue = mrealm.where(details.class).equalTo("title",titlevalue).findAll();
                Log.e("data",messagevalue.toString());
                if(messagevalue.size()==0){
                    Toast.makeText(viewDetails.this, "No data found", Toast.LENGTH_SHORT).show();
                }else{
                    message.setText(messagevalue.get(0).getMessage().toString());
                }
            }
        });

    }
}
