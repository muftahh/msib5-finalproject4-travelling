package com.hacktiv8.travelling3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusSeatsActivity extends AppCompatActivity  {

    private TextView ptName, tanggalWaktu, facility, priceTv, seatTv;
    private List<ImageButton> imageButtons = new ArrayList<>();
    private List<Integer> selectedSeats = new ArrayList<>();
    private int priceFinal = 0;
    private int priceBus;

    DatabaseReference database;
    BusAdapter busAdapter;
    ArrayList<Seat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seats);
        ImageButton done = findViewById(R.id.seat_done);
        ImageButton back = findViewById(R.id.back_bt);

        ptName = findViewById(R.id.pt_name);
        tanggalWaktu = findViewById(R.id.tanggal_waktu);
        facility = findViewById(R.id.facility);
        priceTv = findViewById(R.id.price_tv);
        seatTv = findViewById(R.id.seat_tv);

        Bundle data = getIntent().getExtras();
        String key = data.getString("key");
        String date = data.getString("date");
        String pt_name = data.getString("pt_name");
        String price = data.getString("price");
        String fasilityEt = data.getString("fasility");
        String departure = data.getString("departure");

        String numericString = price.replaceAll("[^\\d]", "");
        priceBus = Integer.parseInt(numericString);

        ptName.setText(pt_name);
        tanggalWaktu.setText(departure + "/" + date);
        facility.setText(fasilityEt);

        String baseUrl = getResources().getString(R.string.base_url);
        database = FirebaseDatabase.getInstance(baseUrl).getReference("bus_data").child(key).child("taken_seat");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Seat seat = dataSnapshot.getValue(Seat.class);
                    if (seat != null) {
                        int seatNumber = seat.getNumber();
                        // Ubah background dan setClickable untuk ImageButton yang sudah terpesan
                        String idButton = "seat" + seatNumber;
                        int resID = getResources().getIdentifier(idButton, "id", getPackageName());
                        ImageButton button = findViewById(resID);

                        // Ubah background
                        button.setBackgroundResource(R.drawable.terpesan);

                        // Set ImageButton tidak dapat diklik
                        button.setClickable(false);
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        for (int i = 1; i <= 24; i++) {
            String idButton = "seat" + i;
            int resID = getResources().getIdentifier(idButton, "id", getPackageName());
            ImageButton button = findViewById(resID);
            imageButtons.add(button);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleSeatSelection((ImageButton) v, finalI);
                }
            });
        }

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

    private void handleSeatSelection(ImageButton imageButton, int seatNumber) {
        if (imageButton.getTag() == null || !((boolean) imageButton.getTag())) {
            if (selectedSeats.size() < 3) {
                selectedSeats.add(seatNumber);
                updateHasil(imageButton);
                priceTv.setText(String.valueOf(priceFinal));

                updateSeatTvText();

                imageButton.setBackgroundResource(R.drawable.seat_fill);
                imageButton.setTag(true);
            } else {
                Toast.makeText(this, "Anda hanya dapat memilih 3 kursi!", Toast.LENGTH_SHORT).show();
            }
        } else {
            selectedSeats.remove(Integer.valueOf(seatNumber));
            updateHasil(imageButton);
            priceTv.setText(String.valueOf(priceFinal));

            updateSeatTvText();

            imageButton.setBackgroundResource(R.drawable.group_15_copy_3);
            imageButton.setTag(false);
        }
    }

    private void updateSeatTvText() {
        StringBuilder selectedSeatsText = new StringBuilder();
        for (int seat : selectedSeats) {
            selectedSeatsText.append("S").append(seat).append(" ");
        }
        seatTv.setText(selectedSeatsText.toString().trim());
    }

    private void updateHasil(ImageButton imageButton) {
        if (imageButton.getTag() == null || !((boolean) imageButton.getTag())) {
            priceFinal += priceBus;
            imageButton.setBackgroundResource(R.drawable.seat_fill);
            imageButton.setTag(true);
        } else {
            priceFinal -= priceBus;
            imageButton.setBackgroundResource(R.drawable.group_15_copy_3);
            imageButton.setTag(false);
        }
    }


    private void applyClickEffect(View view) {
        Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
        clickAnimation.setDuration(100);
        view.startAnimation(clickAnimation);
    }

}