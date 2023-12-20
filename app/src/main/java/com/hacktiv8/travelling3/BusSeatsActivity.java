package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

public class BusSeatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seats);
        ImageButton done = findViewById(R.id.seat_done);
        ImageButton back = findViewById(R.id.back_bt);

        TextView ptName = findViewById(R.id.pt_name);
        TextView tanggalWaktu = findViewById(R.id.tanggal_waktu);
        TextView facility = findViewById(R.id.facility);

        Bundle data = getIntent().getExtras();
        assert data != null;
        String key = data.getString("key");
        String date = data.getString("date");
        String pt_name = data.getString("pt_name");
        String price = data.getString("price");
        String fasilityEt = data.getString("fasility");
        String departure = data.getString("departure");

        ptName.setText(pt_name);
        tanggalWaktu.setText(departure + "/" + date);
        facility.setText(fasilityEt);

        back.setOnClickListener(v -> {
            Bundle data1 = getIntent().getExtras();
            String date1 = data1.getString("mHomeTextInputDateGo");
            String inputFrom = data1.getString("mInputFrom");
            String inputTo = data1.getString("mInputTo");

            Intent intent = new Intent(BusSeatsActivity.this, ListTicketActivity.class);
            intent.putExtra("mHomeTextInputDateGo", date1);
            intent.putExtra("mInputFrom", inputFrom);
            intent.putExtra("mInputTo", inputTo);
            startActivity(intent);
        });


        done.setOnClickListener(v -> {
            Intent intent = new Intent(BusSeatsActivity.this, ScheduleBusActivity.class);

            // Mendapatkan data dari getIntent()
            Bundle dataFromIntent = getIntent().getExtras();
            assert dataFromIntent != null;
            String keyFromIntent = dataFromIntent.getString("key");
            String dateFromIntent = dataFromIntent.getString("date");
            String ptNameFromIntent = dataFromIntent.getString("pt_name");
            String priceFromIntent = dataFromIntent.getString("price");
            String facilityFromIntent = dataFromIntent.getString("fasility");
            String departureFromIntent = dataFromIntent.getString("departure");
            String date1 = dataFromIntent.getString("mHomeTextInputDateGo");
            String inputFrom = dataFromIntent.getString("mInputFrom");
            String inputTo = dataFromIntent.getString("mInputTo");

            // Menambahkan data ke Intent
            intent.putExtra("keyFromIntent", keyFromIntent);
            intent.putExtra("dateFromIntent", dateFromIntent);
            intent.putExtra("ptNameFromIntent", ptNameFromIntent);
            intent.putExtra("priceFromIntent", priceFromIntent);
            intent.putExtra("facilityFromIntent", facilityFromIntent);
            intent.putExtra("departureFromIntent", departureFromIntent);

            intent.putExtra("mHomeTextInputDateGo", date1);
            intent.putExtra("mInputFrom", inputFrom);
            intent.putExtra("mInputTo", inputTo);

            startActivity(intent);
            applyClickEffect(done);
        });

    }

    private void applyClickEffect(View view) {
        Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
        clickAnimation.setDuration(100);
        view.startAnimation(clickAnimation);
    }

}