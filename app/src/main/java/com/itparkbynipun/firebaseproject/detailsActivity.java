package com.itparkbynipun.firebaseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class detailsActivity extends AppCompatActivity {

    private Button submit;
    private EditText title,desc;
    private Realm mrealm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = findViewById(R.id.titleTxt);
        desc = findViewById(R.id.messageTxt);
        submit = findViewById(R.id.submitBtn);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("details.realm").schemaVersion(0).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mrealm = Realm.getInstance(realmConfiguration);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mrealm.beginTransaction();
                details detail = mrealm.createObject(details.class);
                detail.setTitle(title.getText().toString());
                detail.setMessage(desc.getText().toString());
                mrealm.commitTransaction();
                Toast.makeText(detailsActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(detailsActivity.this,viewDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mrealm.close();
    }

}
