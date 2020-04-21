package com.example.thepeoplehostel;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mess_timetable extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView;
    MyMessAdapter myMessAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_timetable);

        Spinner spinner=findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.time_table,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        recyclerView=findViewById(R.id.rv_mess);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String  item=parent.getItemAtPosition(position).toString();
        if(item.equalsIgnoreCase("Monday")){
            myMessAdapter=new MyMessAdapter(this,getMondayList());
            recyclerView.setAdapter(myMessAdapter);
        }
        if(item.equalsIgnoreCase("Tuesday")){
            myMessAdapter=new MyMessAdapter(this,getTuesdayList());
            recyclerView.setAdapter(myMessAdapter);
        }
        if(item.equalsIgnoreCase("Wednesday")){
            myMessAdapter=new MyMessAdapter(this,getWednesdayList());
            recyclerView.setAdapter(myMessAdapter);
        }
        if(item.equalsIgnoreCase("Thursday")){
            myMessAdapter=new MyMessAdapter(this,getThursdayList());
            recyclerView.setAdapter(myMessAdapter);
        }
        if(item.equalsIgnoreCase("Friday")){
            myMessAdapter=new MyMessAdapter(this,getFridayList());
            recyclerView.setAdapter(myMessAdapter);
        }
        if(item.equalsIgnoreCase("Saturday")){
            myMessAdapter=new MyMessAdapter(this,getSaturdayList());
            recyclerView.setAdapter(myMessAdapter);
        }
        if(item.equalsIgnoreCase("Sunday")){
            myMessAdapter=new MyMessAdapter(this,getSundayList());
            recyclerView.setAdapter(myMessAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
    private ArrayList<mess_modal> getMondayList(){
        
        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Pav Bhaji");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Paneer,Arhar Dal");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Bhel Puri");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Aloo ki Subji,Chana Dal");
        modals.add(m_modals);
        
        return modals;
    }
    private ArrayList<mess_modal> getTuesdayList(){

        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Poori Subji");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Chole,Masur Dal");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Noodles");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Aloo Baingan,Kada Dal");
        modals.add(m_modals);

        return modals;
    }
    private ArrayList<mess_modal> getWednesdayList(){

        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Aloo ki kachauri");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Aloo Cabbage,Amritsari Dal");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Dosa");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Kaddu,Masoor Dal");
        modals.add(m_modals);

        return modals;
    }
    private ArrayList<mess_modal> getThursdayList(){

        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Pav Bhaji");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Loki,Arhar Dal");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Fried Rice");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Aloo Jeera,Dal Haryanvi");
        modals.add(m_modals);

        return modals;
    }
    private ArrayList<mess_modal> getFridayList(){

        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Puri Sabji");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Chole Bhature,Raita");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Samosa");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Bhindi ki Subji,Mix Dal");
        modals.add(m_modals);

        return modals;
    }
    private ArrayList<mess_modal> getSaturdayList(){

        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Aloo Parantha");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Palak Paneer,Arhar Dal");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Wada Sambhar");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Malai Kofta,Dal Tadka");
        modals.add(m_modals);

        return modals;
    }
    private ArrayList<mess_modal> getSundayList(){

        ArrayList<mess_modal>modals=new ArrayList<>();
        mess_modal m_modals=new mess_modal();
        m_modals.setDay("Breakfast");
        m_modals.setFood("Aloo kachauri");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Lunch");
        m_modals.setFood("Chole Bhature,Raita");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Snacks");
        m_modals.setFood("Idli");
        modals.add(m_modals);

        m_modals=new mess_modal();
        m_modals.setDay("Dinner");
        m_modals.setFood("Aloo Subji,Chana Dal");
        modals.add(m_modals);

        return modals;
    }
}
