package com.example.thepeoplehostel;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class popupactivity extends AppCompatActivity {

    private TextView name,date,number;
    private Button register;
    private FirebaseAuth firebaseAuth;
    String vname,vdate,vnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupactivity);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width=displayMetrics.widthPixels;
        int height=displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.5));

        name=findViewById(R.id.v_name);
        date=findViewById(R.id.v_date);
        number=findViewById(R.id.v_number);
        register=findViewById(R.id.v_add);

        firebaseAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    senduserdata();
                    Toast.makeText(popupactivity.this, "Request Successfully Send", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    private Boolean validate(){
        boolean result = false;

        vname=name.getText().toString();
        vdate=date.getText().toString();
        vnumber=number.getText().toString();

        if(vname.isEmpty()||vdate.isEmpty()||vnumber.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

    private void senduserdata() {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());
        VisitorEntry visitorentry=new VisitorEntry(vname,vdate,vnumber);
        myref.child("Visitors").push().setValue(visitorentry);
    }
}
