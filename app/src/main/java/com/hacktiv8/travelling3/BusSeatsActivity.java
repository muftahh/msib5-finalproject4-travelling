package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;

public class BusSeatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seats);
        ImageButton done = findViewById(R.id.seat_done);
        ImageButton back = findViewById(R.id.back_bt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusSeatsActivity.this, ListTicketActivity.class);
                startActivity(intent);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusSeatsActivity.this, ScheduleBusActivity.class);
                startActivity(intent);
                applyClickEffect(done);
            }
        });
    }

    private void applyClickEffect(View view) {
        Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
        clickAnimation.setDuration(100);
        view.startAnimation(clickAnimation);
    }

}