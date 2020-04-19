package com.example.thepeoplehostel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class updatepassword extends AppCompatActivity {

    private Button update;
    private TextView password;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepassword);

        update=findViewById(R.id.btn_updatepassword);
        password=findViewById(R.id.et_updatepassword);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userpasswordNew=password.getText().toString();
                firebaseUser.updatePassword(userpasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(updatepassword.this,"Password Changed",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(updatepassword.this,"Password Update Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
