package com.example.thepeoplehostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class visitorsactivity extends AppCompatActivity {

    private Button add;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    List<VisitorEntry>visitorEntries;
    ListView visitors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorsactivity);

        add=findViewById(R.id.btn_addvisitor);
        visitors=findViewById(R.id.list_view);
        visitorEntries=new ArrayList<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(visitorsactivity.this,popupactivity.class));
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid()).child("Visitors");
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                visitorEntries.clear();
                for(DataSnapshot visitorSnapshot : dataSnapshot.getChildren()){
                    VisitorEntry visitorEntry=visitorSnapshot.getValue(VisitorEntry.class);
                    visitorEntries.add(visitorEntry);

                }
                visitor_list adapter = new visitor_list(visitorsactivity.this,visitorEntries);
                visitors.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
