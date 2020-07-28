package com.parkme.parkme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

public class SlotsAvaiable extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    Boolean pres;
    String Are;
    HashMap<String,Boolean> hashMap;
    List<Boolean> LisBooleanList;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        Are=i.getStringExtra("Area");
        hashMap=new HashMap<String,Boolean>();
        pd=new ProgressDialog(SlotsAvaiable.this);
        pd.setMessage("Please Wait...");
        pd.show();
        hashMap=readData();


        setContentView(R.layout.activity_slots_avaiable);



        b1=findViewById(R.id.one);
        b2=findViewById(R.id.two);
        b3=findViewById(R.id.three);
        b4=findViewById(R.id.four);
        b5=findViewById(R.id.five);
        b6=findViewById(R.id.six);
        b7=findViewById(R.id.seven);
        b8=findViewById(R.id.eight);
        b9=findViewById(R.id.nine);
        b10=findViewById(R.id.ten);
        auth=FirebaseAuth.getInstance();
        if(!hashMap.isEmpty()){
            if(hashMap.get("one"))
            b1.setBackgroundResource(R.drawable.selected);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!hashMap.isEmpty()) {
                        if (hashMap.get("one")) {
                            reference.child("one").setValue(false);
                        } else {
                            Toast.makeText(SlotsAvaiable.this, "Slot Already Booked", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
                    }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("two")){
                    reference.child("two").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
                }

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("three")){
                    reference.child("three").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
            }

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("four")){
                    reference.child("four").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
        }else{
            Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
        }
            }

        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("five")){
                    reference.child("five").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!hashMap.isEmpty()) {

                if(hashMap.get("six")){
                    reference.child("six").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
        }else{
        Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
        }

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("seven")){
                    reference.child("seven").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
        }else{
        Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
        }
            }

        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("eight")){
                    reference.child("eight").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
        }else{
        Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
        }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("nine")){
                    reference.child("nine").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }
        }else{
        Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
        }
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.isEmpty()) {
                if(hashMap.get("ten")){
                    reference.child("ten").setValue(false);
                }else{
                    Toast.makeText(SlotsAvaiable.this,"Slot Already Booked" ,Toast.LENGTH_LONG).show();
                }

        }else{
        Toast.makeText(SlotsAvaiable.this,"Please Wait Data is Being Loaded",Toast.LENGTH_LONG).show();
        }
            }
        });
    }
    HashMap readData(){
            final HashMap<String,Boolean>   hhMap=new HashMap<String,Boolean> ();
        reference= FirebaseDatabase.getInstance().getReference().child( "Parking Area" ).child(Are);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot slot:dataSnapshot.getChildren()){
                    hhMap.put(slot.getKey(),(Boolean) slot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pd.dismiss();
        return  hhMap;
    }
}