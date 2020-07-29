package com.parkme.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {
    TextView name,email,area,slote,mobile;
    Button back,cancel;
    DatabaseReference reference,ref;
    String slott;
    FirebaseUser user;
    long sltnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        user= FirebaseAuth.getInstance().getCurrentUser();
        name=findViewById(R.id.name1);
        email=findViewById(R.id.email1);
        area=findViewById(R.id.area);
        slote=findViewById(R.id.slot);
        mobile=findViewById(R.id.phonenumber1);
        back=findViewById(R.id.backx);
        cancel=findViewById(R.id.cancel);
        Intent i=getIntent();
        slott= i.getStringExtra("SlotName");
        name.setText(i.getStringExtra("Name"));
        email.setText(i.getStringExtra("Email"));
        mobile.setText(i.getStringExtra("Mobile"));
        area.setText(i.getStringExtra("Area"));
        sltnum=i.getLongExtra("Slot",0);
        slote.setText(Long.toString(sltnum));
        reference= FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        ref=FirebaseDatabase.getInstance().getReference("Parking Area").child(i.getStringExtra("Area"));


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatusActivity.this,HomeActivity.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("Area").setValue("");
                reference.child("slot").setValue(0);
                ref.child(slott).setValue(true);
                startActivity(new Intent(StatusActivity.this,HomeActivity.class));
            }
        });
    }
}