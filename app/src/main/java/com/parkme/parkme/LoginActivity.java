package com.parkme.parkme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    EditText Email,Pass;
    Button sub;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=findViewById(R.id.name_field);
        Pass=findViewById(R.id.password_field);
        sub=findViewById(R.id.submit112);
        auth=FirebaseAuth.getInstance();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(LoginActivity.this);
                pd.setMessage( "Please Wait" );
                pd.show();

                String Eemail=Email.getText().toString();
                String Pas=Pass.getText().toString();

                if(TextUtils.isEmpty( Eemail )||TextUtils.isEmpty( Pas ))
                {
                    pd.dismiss();
                    Toast.makeText( LoginActivity.this,"Please Fill All The Field" ,Toast.LENGTH_LONG).show();
                }else{

                    auth.signInWithEmailAndPassword( Eemail,Pas ).
                            addOnCompleteListener( LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child( "Users" ).
                                                child( auth.getCurrentUser().getUid() );

                                        ref.addValueEventListener( new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                pd.dismiss();
                                                Intent intent=new Intent( LoginActivity.this,HomeActivity.class );
                                                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK );
                                                startActivity( intent );
                                                finish();
                                            }




                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        } );
                                    }else{
                                        pd.dismiss();
                                        Toast.makeText( LoginActivity.this,"Authentication Failed ",Toast.LENGTH_LONG ).show();
                                    }
                                }
                            } );
                }

            }
        });

    }
}