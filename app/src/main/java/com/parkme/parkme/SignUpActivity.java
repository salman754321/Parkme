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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    ProgressDialog pd;
    FirebaseAuth auth;
    DatabaseReference reference;
    EditText uname,emai,pass,mobile;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uname=findViewById(R.id.name_field);
        emai=findViewById(R.id.email_field);
        pass=findViewById(R.id.password_field);
        mobile=findViewById(R.id.mobile_field);
        auth=FirebaseAuth.getInstance();

        signup=findViewById(R.id.submit1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(SignUpActivity.this  );
                pd.setMessage( "Please Wait..." );
                pd.show();


                String unam= uname.getText().toString();
                String mobil=mobile.getText().toString();
                String email=emai.getText().toString();
                String pas=pass.getText().toString();

                if(TextUtils.isEmpty( unam )|| TextUtils.isEmpty( mobil )||TextUtils.isEmpty( email )|| TextUtils.isEmpty( pas )){
                    pd.dismiss();
                    Toast.makeText( SignUpActivity.this,"Fill All Fields",Toast.LENGTH_LONG ).show();
                }else if(pass.length()<6)
                {
                    pd.dismiss();
                    Toast.makeText( SignUpActivity.this, "Password Must Contain 6 Or More Characters",Toast.LENGTH_LONG).show();
                }else{
                    Register(unam,pas,mobil,email);
                }

            }
        });
    }

    public void Register(final String unam, String pas, final String mobil, final String email) {
        auth.createUserWithEmailAndPassword( email,pas ).addOnCompleteListener( SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseuser=auth.getCurrentUser();
                    String userId=firebaseuser.getUid();

                    reference= FirebaseDatabase.getInstance().getReference().child( "Users" ).child(userId);
                    HashMap<String,Object> hashMap=new HashMap<>(  );
                    hashMap.put( "UserId",userId );
                    hashMap.put( "UserName",unam.toLowerCase() );
                    hashMap.put("Mobile",mobil);
                    hashMap.put("Email",email);
                    hashMap.put("Slot",0);
                    ;
                    reference.setValue( hashMap ).addOnCompleteListener( new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                pd.dismiss();
                                Intent intent=new Intent( SignUpActivity.this,HomeActivity.class );
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    } );


                }
                else {
                        pd.dismiss();
                    Toast.makeText( SignUpActivity.this,"You Can't Register With This Email Or UserName Or PassWord",Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }
}
