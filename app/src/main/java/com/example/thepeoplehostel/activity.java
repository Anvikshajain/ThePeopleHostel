package com.example.thepeoplehostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        Button mess = findViewById(R.id.btn_mess);
        Button gpass = findViewById(R.id.btn_gpass);

        gpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.this, "Gate Pass Request Sent", Toast.LENGTH_SHORT).show();
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity.this,mess_timetable.class));
            }
        });
    }
}