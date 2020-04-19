package com.example.thepeoplehostel;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class profileactivity extends AppCompatActivity {

    private TextView profileName, profileEmail,profileRoom,profilePhone,profileEnroll;
    private CircularImageView circularImageView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        profileName = findViewById(R.id.name_textview);
        profileEmail = findViewById(R.id.et_mail);
        profileRoom=findViewById(R.id.et_roomno);
        profilePhone=findViewById(R.id.et_phone);
        profileEnroll=findViewById(R.id.et_enrno);
        Button profileUpdate = findViewById(R.id.editprofile_button);
        Button changePassword = findViewById(R.id.btn_changepassword);
        circularImageView = findViewById(R.id.profile_imageview);
        circularImageView.setCircleColor(Color.WHITE);
        circularImageView.setBorderWidth(5f);
        circularImageView.setBorderColor(Color.BLACK);
        circularImageView.setShadowEnable(false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            databaseReference = firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));
        }

        StorageReference storageReference= firebaseStorage.getReference();
        storageReference.child(Objects.requireNonNull(firebaseAuth.getUid())).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(circularImageView);
            }
        });

        assert databaseReference != null;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                assert userProfile != null;
                profileName.setText(userProfile.getUserName());
                profileEmail.setText(userProfile.getUserEmail());
                profilePhone.setText(userProfile.getUserPhone());
                profileEnroll.setText(userProfile.getUserEnroll());
                profileRoom.setText(userProfile.getUserRoom());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profileactivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profileactivity.this, updateprofile.class));
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profileactivity.this, updatepassword.class));
            }
        });
    }
}