package com.example.thepeoplehostel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class registrationactivity extends AppCompatActivity {

    private EditText name,college,branch,room,phone,enroll,email,password;
    private FirebaseAuth firebaseAuth;
    private String uname, ucollege,ubranch,uroom,uphone,uenroll,uemail,upassword;
    private ImageView userProilePic;
    Button register;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE=123;
    Uri imagePath;
    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                userProilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationactivity);
        setupUIViews();

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        storageReference=firebaseStorage.getReference();
        userProilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("images/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(registrationactivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(registrationactivity.this, loginactivity.class));
                            }else{
                                Toast.makeText(registrationactivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }

    private void setupUIViews() {
        name = findViewById(R.id.register_name);
        college = findViewById(R.id.register_college);
        branch = findViewById(R.id.register_branch);
        room = findViewById(R.id.register_room);
        phone = findViewById(R.id.register_phone);
        enroll = findViewById(R.id.register_enroll);
        email = findViewById(R.id.register_email);
        password=findViewById(R.id.et_password);
        register = findViewById(R.id.register_button);
        userProilePic= findViewById(R.id.register_profile);

    }

    private Boolean validate(){
        boolean result = false;

        upassword = password.getText().toString();
        uname= name.getText().toString();
        uemail= email.getText().toString();
        uenroll= enroll.getText().toString();
        ucollege= college.getText().toString();
        uphone= phone.getText().toString();
        uroom= room.getText().toString();
        ubranch= branch.getText().toString();


        if(uname.isEmpty() || upassword.isEmpty() || uemail.isEmpty() || uenroll.isEmpty()||ucollege.isEmpty()||uphone.isEmpty()||uroom.isEmpty()
        ||ubranch.isEmpty()||imagePath==null){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference =storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");
        UploadTask uploadTask=imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registrationactivity.this,"Upload Failed!",Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(registrationactivity.this, "Upload Successful!", Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile=new UserProfile(uname,uemail,uenroll,ucollege,uphone,uroom,ubranch);
        myref.setValue(userProfile);
    }
}