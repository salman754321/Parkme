package com.parkme.parkme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    ProgressBar bar;
    FirebaseUser user;
    String sl;
    String Email,Phone;
    String namee=null;
    long slo=-1;
    ProgressDialog pd;
    String Areaa=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user=FirebaseAuth.getInstance().getCurrentUser();
        name=findViewById(R.id.nameid);
        name.setVisibility(View.GONE);

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
                 Areaa=(String) dataSnapshot.child("Area").getValue();
                 Email=(String) dataSnapshot.child("Email").getValue();
                 Phone=(String) dataSnapshot.child("Mobile").getValue();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait");
        pd.show();

        pd.dismiss();
        name.setText(user.getEmail());
        name.setVisibility(View.VISIBLE);
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
                if (slo == -1) {
                    Toast.makeText(HomeActivity.this, "Please Wait.... ", Toast.LENGTH_SHORT).show();
                }else
                if (slo == 0) {
                    Toast.makeText(HomeActivity.this, "You Don't Have Any Booking", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i=new Intent(HomeActivity.this, StatusActivity.class);
                    if(slo==1){
                        sl="one";
                    }else if(slo==2){
                        sl="two";
                    }else if(slo==3){
                        sl="three";
                    }else if(slo==4){
                        sl="four";
                    }else if(slo==5){
                        sl="five";
                    }else if(slo==6){
                        sl="six";
                    }else if(slo==7){
                        sl="seven";
                    }else if(slo==8){
                        sl="eight";
                    }else if(slo==9){
                        sl="nine";
                    }else if(slo==10){
                        sl="ten";
                    }
                    i.putExtra("Area",Areaa);
                    i.putExtra("Slot",slo);
                    i.putExtra("Name",namee);
                    i.putExtra("Email",Email);
                    i.putExtra("Mobile",Phone);
                    i.putExtra("SlotName",sl);
                    startActivity(i);
                }
            }
        });
    }

}