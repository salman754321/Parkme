package com.parkme.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AreaList extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_list);

        b1=findViewById(R.id.Coms1);
        b2=findViewById(R.id.Coms2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AreaList.this,SlotsAvaiable.class);
                i.putExtra("Area","Comsats Atd");
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AreaList.this,SlotsAvaiable.class);
                i.putExtra("Area","Comsats Isb");
                startActivity(i);
            }
        });

    }
}