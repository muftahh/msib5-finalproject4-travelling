package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailPaymentActivity extends AppCompatActivity {

    private String Seat = "";
    private  int sumSeat= 0;


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

        ImageView cancel = findViewById(R.id.buttonBackDetailPayment);

        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(DetailPaymentActivity.this, MainActivity.class);
            Toast.makeText(DetailPaymentActivity.this, "Pembelian dibatalkan", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<Integer> selectedSeats = intent.getIntegerArrayListExtra("selectedSeats");
            if (selectedSeats != null && !selectedSeats.isEmpty()) {
                StringBuilder seatStringBuilder = new StringBuilder();
                for (int seat : selectedSeats) {
                    seatStringBuilder.append("S").append(seat).append(" ");
                    sumSeat += 1;
                }
                Seat = seatStringBuilder.toString().trim();
                passangerSeat.setText(Seat);
            }

        }

        Bundle data = getIntent().getExtras();
        assert data != null;
        String key = data.getString("keyFromIntent");
        String name = data.getString("passengerName");
        String date = data.getString("mHomeTextInputDateGo");
        String facility = data.getString("facilityFromIntent");
        String ptName = data.getString("ptNameFromIntent");
        String from = data.getString("mInputFrom");
        String to = data.getString("mInputTo");
        String departure = data.getString("departureFromIntent");

        int numberSeat = data.getInt("numberSeat");

        int finalPrice = data.getInt("priceTicket");

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(finalPrice);

        passangerName.setText(name);
        passangerBus.setText(ptName);
        passangerSeat.setText(Seat);
        passangerFrom.setText(from);
        passangerTo.setText(to);
        passangerTime.setText(departure);
        passangerDate.setText(date);
        passangerPrice.setText(formattedPrice);

        ConstraintLayout indomaretLayout = findViewById(R.id.indomaret);
        indomaretLayout.setOnClickListener(view -> {
            Intent intent1 = new Intent(DetailPaymentActivity.this, TransactionActivity.class);
            intent1.putExtra("keyBus", key);
            intent1.putExtra("ptBus", ptName);
            intent1.putExtra("userName", name);
            intent1.putExtra("from", from);
            intent1.putExtra("destination", to);
            intent1.putExtra("date", date);
            intent1.putExtra("time", departure);
            intent1.putExtra("seat", Seat);
            intent1.putExtra("sumSeat", sumSeat);
            intent1.putExtra("price", finalPrice);
            intent1.putExtra("clas", "Ekonomi");
            intent1.putExtra("facility", facility);
            intent1.putExtra("numberSeat", numberSeat);
            startActivity(intent1);

        });
    }

}