package com.example.thepeoplehostel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class visitor_list extends ArrayAdapter<VisitorEntry> {
        private Activity context;
        private List<VisitorEntry>visitorList;

        public visitor_list(Activity context,List<VisitorEntry>visitorList){
            super(context,R.layout.row,visitorList);
            this.context=context;
            this.visitorList=visitorList;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.row,null,true);
        TextView textViewName =listViewItem.findViewById(R.id.tv);
        TextView textViewDate =listViewItem.findViewById(R.id.date);

        VisitorEntry visitorEntry=visitorList.get(position);

        textViewName.setText(visitorEntry.getName());
        textViewDate.setText(visitorEntry.getDate());

        return listViewItem;
    }
}
