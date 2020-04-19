package com.example.thepeoplehostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotactivity extends AppCompatActivity {

    private EditText passwordEmail;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotactivity);

        passwordEmail= findViewById(R.id.et_resetemail);
        Button resetpassword = findViewById(R.id.btn_reset);
        firebaseAuth=FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=passwordEmail.getText().toString().trim();
                if(useremail.equals("")){
                    Toast.makeText(forgotactivity.this,"Please enter your Email ID",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(forgotactivity.this,"Password Reset Email Sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotactivity.this,loginactivity.class));
                            }else{
                                Toast.makeText(forgotactivity.this, "Error in sending Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}