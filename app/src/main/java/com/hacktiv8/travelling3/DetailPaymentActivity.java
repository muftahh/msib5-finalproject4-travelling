package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailPaymentActivity extends AppCompatActivity {

    private String Seat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);

        TextView passangerName = findViewById(R.id.detailPaymentName);
        TextView passangerBus = findViewById(R.id.detailPaymentBusName);
        TextView passangerSeat = findViewById(R.id.detailPaymentBusSeats);
        TextView passangerFrom = findViewById(R.id.detailPaymentFrom);
        TextView passangerTo = findViewById(R.id.detailPaymentTo);
        TextView passangerTime = findViewById(R.id.detailPaymentTime);
        TextView passangerDate = findViewById(R.id.detailPaymentDate);
        TextView passangerPrice = findViewById(R.id.detailPaymentPrice);

        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<Integer> selectedSeats = intent.getIntegerArrayListExtra("selectedSeats");
            if (selectedSeats != null && !selectedSeats.isEmpty()) {
                StringBuilder seatStringBuilder = new StringBuilder();
                for (int seat : selectedSeats) {
                    seatStringBuilder.append(seat).append(" ");
                }
                Seat = seatStringBuilder.toString().trim();
                passangerSeat.setText(Seat);
            }

        }

        Bundle data = getIntent().getExtras();
        assert data != null;
        String name = data.getString("passengerName");
        String date = data.getString("mHomeTextInputDateGo");
        String ptName = data.getString("ptNameFromIntent");
        String from = data.getString("mInputFrom");
        String to = data.getString("mInputTo");
        String departure = data.getString("departureFromIntent");
        int finalPrice = data.getInt("priceTicket");

        passangerName.setText(name);
        passangerBus.setText(ptName);
        passangerSeat.setText(Seat);
        passangerFrom.setText(from);
        passangerTo.setText(to);
        passangerTime.setText(departure);
        passangerDate.setText(date);
        passangerPrice.setText(String.valueOf(finalPrice));





        ConstraintLayout indomaretLayout = findViewById(R.id.indomaret);
        indomaretLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay(view);
            }
        });
    }

    // Metode yang akan dipanggil saat tombol diklik
    public void pay(View view) {
        // Logika yang ingin Anda jalankan saat tombol diklik
        Toast.makeText(this, "Button Indomaret Clicked", Toast.LENGTH_SHORT).show();
        // Tambahan logika atau aksi yang diinginkan
    }
}