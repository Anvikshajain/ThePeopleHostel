package com.example.thepeoplehostel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyMessAdapter extends RecyclerView.Adapter<MyMessHolder> {

    Context c;
    ArrayList<mess_modal> models;

    public MyMessAdapter(Context c,ArrayList<mess_modal>models){
        this.c=c;
        this.models=models;
    }

    @NonNull
    @Override
    public MyMessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.m_timetable,null);
        return new MyMessHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMessHolder holder, int position) {

        holder.mday.setText(models.get(position).getDay());
        holder.mfood.setText(models.get(position).getFood());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
