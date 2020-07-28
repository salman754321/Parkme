package com.parkme.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    Button logout,book,status;
    TextView name;
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user=FirebaseAuth.getInstance().getCurrentUser();
        name=findViewById(R.id.nameid);
        name.setText(user.getDisplayName());
        Log.i("fname ",user.getDisplayName());
        logout=findViewById(R.id.logout_button);
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent( HomeActivity.this, MainActivity.class ));
            }
        } );


        book=findViewById(R.id.list);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( HomeActivity.this, AreaList.class ));
            }
        });

        status=findViewById(R.id.statusbutton);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}