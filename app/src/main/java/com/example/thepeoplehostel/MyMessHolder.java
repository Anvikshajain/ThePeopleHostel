package com.example.thepeoplehostel;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyMessHolder extends RecyclerView.ViewHolder {

    TextView mday,mfood;

    public MyMessHolder(@NonNull View itemView) {
        super(itemView);

        this.mday=itemView.findViewById(R.id.tv_day);
        this.mfood=itemView.findViewById(R.id.food);
    }
}
