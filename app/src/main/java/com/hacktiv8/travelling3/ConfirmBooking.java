package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.Toast;

public class ConfirmBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);

        ImageButton success = findViewById(R.id.success_bt);
        ImageButton failed = findViewById(R.id.failed_bt);

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyClickEffect(success);
                Intent intent = new Intent(ConfirmBooking.this, MainActivity.class);
                startActivity(intent);
            }

            private void applyClickEffect (View view){
                Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
                clickAnimation.setDuration(100);
                view.startAnimation(clickAnimation);
            }
        });

        failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyClickEffect(failed);
                Intent intent = new Intent(ConfirmBooking.this, MainActivity.class);
                startActivity(intent);
            }
            private void applyClickEffect (View view){
                Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
                clickAnimation.setDuration(100);
                view.startAnimation(clickAnimation);
            }
        });



    }
}