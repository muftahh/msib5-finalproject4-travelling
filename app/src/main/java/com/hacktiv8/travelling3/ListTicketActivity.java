package com.hacktiv8.travelling3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListTicketActivity extends AppCompatActivity implements BusAdapter.listItemClickListener {

    private RecyclerView tiketListRv;
    DatabaseReference database;
    BusAdapter busAdapter;
    ArrayList<Bus> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ticket);
        Bundle data = getIntent().getExtras();
        String date = data.getString("mHomeTextInputDateGo");
        String inputFrom = data.getString("mInputFrom");
        String inputTo = data.getString("mInputTo");

        TextView from = findViewById(R.id.from_tv);
        TextView destination = findViewById(R.id.to_tv);

        from.setText(inputFrom);
        destination.setText(inputTo);



        tiketListRv = findViewById(R.id.listticketRv);
        String baseUrl = getResources().getString(R.string.base_url);
        database = FirebaseDatabase.getInstance(baseUrl).getReference("bus_data");
        tiketListRv.setHasFixedSize(true);
        tiketListRv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        busAdapter = new BusAdapter(this, list);
        busAdapter.setListener(this);
        tiketListRv.setAdapter(busAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String busKey = dataSnapshot.getKey();
                    Bus bus = dataSnapshot.getValue(Bus.class);
                    if (inputFrom.equals(bus.getCity_from())
                            && inputTo.equals(bus.getCity_to())
                            && date.equals(bus.getDate())){
                        bus.setKey(busKey);
                        list.add(bus);
                    }

                }
                busAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ImageButton back = findViewById(R.id.back_bt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTicketActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onListItemClick(View v, int position) {
        Bus busSelected = list.get(position);
        Intent intent = new Intent(this, BusSeatsActivity.class);
        intent.putExtra("key", busSelected.getKey());
        intent.putExtra("pt_name", busSelected.getPt_name());
        intent.putExtra("date", busSelected.getDate());
        intent.putExtra("price", busSelected.getPrice());
        intent.putExtra("fasility", busSelected.getFacility());
        intent.putExtra("departure", busSelected.getDeparture());

        startActivity(intent);
    }
}