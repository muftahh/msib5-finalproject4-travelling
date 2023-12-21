package com.hacktiv8.travelling3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class YourTicketActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_ticket);

        auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        String baseUrl = getResources().getString(R.string.base_url);
        FirebaseDatabase database = FirebaseDatabase.getInstance(baseUrl);
        myRef = database.getReference().child("users")
                .child(uid)
                .child("Ticket");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<YourTicketModel> dataArrayList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren() ) {

                    String asalBis = snapshot.child("asalBis").getValue(String.class);
                    String clasBis = snapshot.child("classBis").getValue(String.class);
                    String harga = snapshot.child("harga").getValue(String.class);
                    String jamPergi = snapshot.child("jamPergi").getValue(String.class);
                    String namaBis = snapshot.child("namaBis").getValue(String.class);
                    String namaPenumpang = snapshot.child("namaPenumpang").getValue(String.class);
                    String noKursi = snapshot.child("noKursi").getValue(String.class);
                    String tanggalKeberangkatan = snapshot.child("tanggalKeberangkatan").getValue(String.class);
                    String tujuanBis = snapshot.child("tujuanBis").getValue(String.class);
                    String tanggalPembelian = snapshot.child("tanggalPembelian").getValue(String.class);
                    String nomerBooking = snapshot.child("nomerBooking").getValue(String.class);
                    String fasilitasBus = snapshot.child("fasilitasBus").getValue(String.class);
                    String keyBus = snapshot.child("keyBus").getValue(String.class);

                    dataArrayList.add(new YourTicketModel(namaPenumpang, asalBis, tujuanBis,
                            tanggalKeberangkatan, clasBis, noKursi, namaBis, jamPergi, harga,
                            tanggalPembelian,nomerBooking, fasilitasBus, keyBus));
                }

                YourTicketAdapter adapter = new YourTicketAdapter(YourTicketActivity.this, R.layout.item_your_ticket, dataArrayList);
                ListView listView = findViewById(R.id.listYourTicket);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(YourTicketActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ListView listView = findViewById(R.id.listYourTicket);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                YourTicketModel clickedItem = (YourTicketModel) parent.getItemAtPosition(position);

                Intent intent = new Intent(YourTicketActivity.this, FinalTicketActivity.class);
                intent.putExtra("iasalBis", clickedItem.getAsalBis());
                intent.putExtra("iclassBis", clickedItem.getClassBis());
                intent.putExtra("iharga", clickedItem.getHarga());
                intent.putExtra("ijamPergi", clickedItem.getJamPergi());
                intent.putExtra("inamaBis", clickedItem.getNamaBis());
                intent.putExtra("inamaPenumpang", clickedItem.getNamaPenumpang());
                intent.putExtra("inoKursi", clickedItem.getNoKursi());
                intent.putExtra("itanggalKeberangkatan", clickedItem.getTanggalKeberangkatan());
                intent.putExtra("itujuanBis", clickedItem.getTujuanBis());
                intent.putExtra("fasilitasBus", clickedItem.getFasilitasBus());
                intent.putExtra("keyBus", clickedItem.getKeyBus());
                startActivity(intent);
            }
        });

    }



}