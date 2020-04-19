package com.example.thepeoplehostel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginactivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView register;
    String lname,lpassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        Name=findViewById(R.id.et_username);
        Password=findViewById(R.id.et_password);
        Button login = findViewById(R.id.btn_login);
        TextView forgotpassword = findViewById(R.id.et_frgt);
        register=findViewById(R.id.btn_register);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(loginactivity.this, homeactivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFilled()) {
                    validate(Name.getText().toString(), Password.getText().toString());
                }
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginactivity.this, forgotactivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginactivity.this,registrationactivity.class));
            }
        });
    }
    private void validate(String userName ,String  userPassword){
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    startActivity(new Intent(loginactivity.this, homeactivity.class));
                    finish();
                }else {
                    Toast.makeText(loginactivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
    private Boolean isFilled(){
        boolean result=false;
        lname=Name.getText().toString();
        lpassword=Password.getText().toString();
        if(lname.isEmpty()||lpassword.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
}