package com.example.thepeoplehostel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class updateprofile extends AppCompatActivity {

    private EditText newUserName, newUserEmail, newUserPhone,newUserRoom,newUserCollege,newUserBranch,newUserEnroll;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private CircularImageView circularImageView;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                circularImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        newUserName = findViewById(R.id.up_name);
        newUserEmail = findViewById(R.id.up_email);
        newUserPhone=findViewById(R.id.up_phone);
        newUserRoom=findViewById(R.id.up_room);
        newUserEnroll=findViewById(R.id.up_enroll);
        newUserCollege=findViewById(R.id.up_college);
        newUserBranch=findViewById(R.id.up_branch);
        save = findViewById(R.id.btn_upprofile);
        circularImageView = findViewById(R.id.up_profile);
        circularImageView.setCircleColor(Color.WHITE);
        circularImageView.setBorderWidth(5f);
        circularImageView.setBorderColor(Color.BLACK);
        circularImageView.setShadowEnable(false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUserName.setText(userProfile.getUserName());
                newUserEmail.setText(userProfile.getUserEmail());
                newUserPhone.setText(userProfile.getUserPhone());
                newUserRoom.setText(userProfile.getUserRoom());
                newUserEnroll.setText(userProfile.getUserEnroll());
                newUserCollege.setText(userProfile.getUserCollege());
                newUserBranch.setText(userProfile.getUserBranch());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(updateprofile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        final StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(circularImageView);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newUserName.getText().toString();
                String email = newUserEmail.getText().toString();
                String phone = newUserPhone.getText().toString();
                String room = newUserRoom.getText().toString();
                String Enroll=newUserEnroll.getText().toString();
                String College=newUserCollege.getText().toString();
                String Branch=newUserBranch.getText().toString();

                UserProfile userProfile = new UserProfile(name,email,Enroll,College,phone,room,Branch);

                databaseReference.setValue(userProfile);

                StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
                UploadTask uploadTask = imageReference.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(updateprofile.this, "Upload failed!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        Toast.makeText(updateprofile.this, "Upload successful!", Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });

        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("images/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });
    }
}