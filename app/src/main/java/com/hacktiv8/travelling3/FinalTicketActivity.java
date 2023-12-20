package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FinalTicketActivity extends AppCompatActivity {
    private TextView userNameTicket, fromTicket, toTicket, dateTicket, classTicket, seatT, busTicket, timeTicket, hargaTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_ticket);

        userNameTicket = findViewById(R.id.userNameTicket);
        fromTicket = findViewById(R.id.fromTicket);
        toTicket = findViewById(R.id.toTicket);
        dateTicket = findViewById(R.id.dateTicket);
        classTicket = findViewById(R.id.classTicket);
        seatT = findViewById(R.id.seatT);
        busTicket = findViewById(R.id.busTicket);
        timeTicket = findViewById(R.id.timeTicket);
        hargaTicket = findViewById(R.id.hargaTicket);

        //Intent put Extra
        userNameTicket.setText(getIntent().getStringExtra("inamaPenumpang"));
        fromTicket.setText(getIntent().getStringExtra("iasalBis"));
        toTicket.setText(getIntent().getStringExtra("itujuanBis"));
        dateTicket.setText(getIntent().getStringExtra("itanggalKeberangkatan"));
        classTicket.setText(getIntent().getStringExtra("iclassBis"));
        seatT.setText(getIntent().getStringExtra("inoKursi"));
        busTicket.setText(getIntent().getStringExtra("inamaBis"));
        timeTicket.setText(getIntent().getStringExtra("ijamPergi"));
        hargaTicket.setText("Rp. " + getIntent().getStringExtra("iharga"));

    }
}