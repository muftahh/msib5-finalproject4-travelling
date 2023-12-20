package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScheduleBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_bus);

        ImageButton boarding = findViewById(R.id.boarding);
        ImageButton dropping = findViewById(R.id.dropping);
        ImageButton back = findViewById(R.id.back_bt);
        TextView next = findViewById(R.id.next);

        FrameLayout contentDefault = findViewById(R.id.content_frame);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new BoardingFragment()).commit();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleBusActivity.this, BusSeatsActivity.class);

                // Mendapatkan data dari getIntent()
                Bundle data = getIntent().getExtras();
                assert data != null;
                String key = data.getString("keyFromIntent");
                String date = data.getString("dateFromIntent");
                String pt_name = data.getString("ptNameFromIntent");
                String price = data.getString("priceFromIntent");
                String fasilityEt = data.getString("facilityFromIntent");
                String departure = data.getString("departureFromIntent");
                String date1 = data.getString("mHomeTextInputDateGo");
                String inputFrom = data.getString("mInputFrom");
                String inputTo = data.getString("mInputTo");

                // Menambahkan data ke Intent
                intent.putExtra("key", key);
                intent.putExtra("date", date);
                intent.putExtra("pt_name", pt_name);
                intent.putExtra("price", price);
                intent.putExtra("fasility", fasilityEt);
                intent.putExtra("departure", departure);

                intent.putExtra("mHomeTextInputDateGo", date1);
                intent.putExtra("mInputFrom", inputFrom);
                intent.putExtra("mInputTo", inputTo);

                startActivity(intent);
                finish();
            }
        });
        boarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boarding.setBackgroundResource(R.color.gray);
                dropping.setBackgroundResource(R.color.white);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new BoardingFragment()).commit();
            }
        });

        dropping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boarding.setBackgroundResource(R.color.white);
                dropping.setBackgroundResource(R.color.gray);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DroppingFragment()).commit();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleBusActivity.this, OrderActivity.class);

                // Mendapatkan data dari getIntent()
                Bundle data = getIntent().getExtras();
                assert data != null;
                String key = data.getString("keyFromIntent");
                String date = data.getString("dateFromIntent");
                String pt_name = data.getString("ptNameFromIntent");
                String price = data.getString("priceFromIntent");
                String fasilityEt = data.getString("facilityFromIntent");
                String departure = data.getString("departureFromIntent");
                String date1 = data.getString("mHomeTextInputDateGo");
                String inputFrom = data.getString("mInputFrom");
                String inputTo = data.getString("mInputTo");

                // Menambahkan data ke Intent
                intent.putExtra("key", key);
                intent.putExtra("date", date);
                intent.putExtra("pt_name", pt_name);
                intent.putExtra("price", price);
                intent.putExtra("fasility", fasilityEt);
                intent.putExtra("departure", departure);

                intent.putExtra("mHomeTextInputDateGo", date1);
                intent.putExtra("mInputFrom", inputFrom);
                intent.putExtra("mInputTo", inputTo);

                startActivity(intent);
            }
        });
    }
}