package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalTicketActivity extends AppCompatActivity {
    private TextView userNameTicket, fromTicket, toTicket, dateTicket, classTicket, seatT, busTicket, timeTicket, hargaTicket, btJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_ticket);

        userNameTicket = findViewById(R.id.userNameTicket);
        classTicket = findViewById(R.id.classTicket);
        hargaTicket = findViewById(R.id.hargaTicket);
        fromTicket = findViewById(R.id.fromTicket);
        dateTicket = findViewById(R.id.dateTicket);
        timeTicket = findViewById(R.id.timeTicket);
        busTicket = findViewById(R.id.busTicket);
        btJadwal = findViewById(R.id.bt_jadwal);
        toTicket = findViewById(R.id.toTicket);
        seatT = findViewById(R.id.seatT);

        //Intent put Extra
        seatT.setText(getIntent().getStringExtra("inoKursi"));
        busTicket.setText(getIntent().getStringExtra("inamaBis"));
        hargaTicket.setText(getIntent().getStringExtra("iharga"));
        fromTicket.setText(getIntent().getStringExtra("iasalBis"));
        toTicket.setText(getIntent().getStringExtra("itujuanBis"));
        timeTicket.setText(getIntent().getStringExtra("ijamPergi"));
        classTicket.setText(getIntent().getStringExtra("iclassBis"));
        userNameTicket.setText(getIntent().getStringExtra("inamaPenumpang"));
        dateTicket.setText(getIntent().getStringExtra("itanggalKeberangkatan"));
        btJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalTicketActivity.this, ScheduleBusActivity.class);
                intent.putExtra("ptNameFromIntent", getIntent().getStringExtra("inamaBis"));
                intent.putExtra("facilityFromIntent", getIntent().getStringExtra("fasilitasBus"));
                intent.putExtra("keyFromIntent", getIntent().getStringExtra("keyBus"));
                intent.putExtra("viewOnly", true);
                startActivity(intent);
            }
        });
    }
}