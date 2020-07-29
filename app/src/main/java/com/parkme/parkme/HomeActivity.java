package com.parkme.parkme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    Button logout,book,status;
    TextView name;
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseUser user;
    String namee;
    long slo=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user=FirebaseAuth.getInstance().getCurrentUser();
        name=findViewById(R.id.nameid);
        name.setText(user.getEmail());
        Log.d("fname ",user.getEmail());
        System.out.println(user.getDisplayName());
        logout=findViewById(R.id.logout_button);
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent( HomeActivity.this, MainActivity.class ));
            }
        } );

    reference=FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 slo = (long) dataSnapshot.child("slot").getValue();
                 namee = (String) dataSnapshot.child("UserName").getValue();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        book=findViewById(R.id.list);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slo == -1) {
                    Toast.makeText(HomeActivity.this, "Please Wait.... ", Toast.LENGTH_SHORT).show();
                }else
                if (slo != 0) {
                    Toast.makeText(HomeActivity.this, "You Have Already Booked the Slot", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(HomeActivity.this, AreaList.class));
                }

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