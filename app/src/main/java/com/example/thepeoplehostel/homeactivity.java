package com.example.thepeoplehostel;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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


public class homeactivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView name,enrid;
    private FirebaseStorage firebaseStorage;
    private CircularImageView circularImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        name=findViewById(R.id.username_home);
        enrid=findViewById(R.id.enrid_home);
        Button settings=findViewById(R.id.btn_settings);
        Button profile=findViewById(R.id.btn_profile);
        Button fees=findViewById(R.id.btn_fees);
        Button navigate=findViewById(R.id.btn_navigate);
        ImageView logout1 = findViewById(R.id.logout);
        circularImageView = findViewById(R.id.mainlogo_splash);
        circularImageView.setCircleColor(Color.WHITE);
        circularImageView.setBorderWidth(5f);
        circularImageView.setBorderColor(Color.BLACK);
        circularImageView.setShadowEnable(false);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        DatabaseReference databaseReference= firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference=firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(circularImageView);
            }
        });
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(homeactivity.this,loginactivity.class));
                Toast.makeText(homeactivity.this,"You have been successfully logged out",Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                assert userProfile!=null;
                name.setText(userProfile.getUserName());
                enrid.setText(userProfile.getUserEnroll());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(homeactivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeactivity.this,visitorsactivity.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeactivity.this,profileactivity.class));
            }
        });
        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(homeactivity.this,feesactivity.class));
            }
        });
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeactivity.this,navigationactivity.class));
            }
        });
    }
}