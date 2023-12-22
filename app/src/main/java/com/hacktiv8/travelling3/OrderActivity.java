package com.hacktiv8.travelling3;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    private MaterialButton order;

    private List<Integer> selectedSeats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        order = findViewById(R.id.order_bt);
        ImageButton back = findViewById(R.id.back_bt);

        // Mendapatkan instance Firebase dan referensi ke /users/(uid)/profile
        String url = "https://hacktiv8-finalproject-default-rtdb.asia-southeast1.firebasedatabase.app/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        DatabaseReference userRef = database.getReference("users/" + uid + "/profile");

        // Mendapatkan referensi ke EditText
        EditText passangerName = findViewById(R.id.passager_name);
        EditText passangerPhone = findViewById(R.id.passager_phone);

        // Membaca data dari Firebase
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Mendapatkan nilai dari Firebase
                String name = dataSnapshot.child("name").getValue(String.class);
                String phone = dataSnapshot.child("nohp").getValue(String.class);

                // Menampilkan nilai pada EditText
                passangerName.setText(name);
                passangerPhone.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Menampilkan pesan error jika terjadi kesalahan
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });


        Intent intent = getIntent();
        if (intent != null) {
            selectedSeats = intent.getIntegerArrayListExtra("selectedSeats");
        }

            back.setOnClickListener(v -> {
                Intent intent1 = new Intent(OrderActivity.this, ScheduleBusActivity.class);

                // Mendapatkan data dari getIntent()
                Bundle data = getIntent().getExtras();
                assert data != null;
                String key = data.getString("key");
                String date = data.getString("date");
                String pt_name = data.getString("pt_name");
                String price = data.getString("price");
                String fasilityEt = data.getString("fasility");
                String departure = data.getString("departure");


                String date1 = data.getString("mHomeTextInputDateGo");
                String inputFrom = data.getString("mInputFrom");
                String inputTo = data.getString("mInputTo");

                // Menambahkan data ke Intent
                intent1.putExtra("keyFromIntent", key);
                intent1.putExtra("dateFromIntent", date);
                intent1.putExtra("ptNameFromIntent", pt_name);
                intent1.putExtra("priceFromIntent", price);
                intent1.putExtra("facilityFromIntent", fasilityEt);
                intent1.putExtra("departureFromIntent", departure);


                intent1.putExtra("mHomeTextInputDateGo", date1);
                intent1.putExtra("mInputFrom", inputFrom);
                intent1.putExtra("mInputTo", inputTo);

                startActivity(intent1);

            });
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    applyClickEffect(order);

                    String passengerName = passangerName.getText().toString();
                    String passengerPhone = passangerPhone.getText().toString();

                    if (passengerName.isEmpty() || passengerPhone.isEmpty()) {
                        Toast.makeText(OrderActivity.this, "Masukkan data", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(OrderActivity.this, DetailPaymentActivity.class);

                        Bundle data = getIntent().getExtras();
                        assert data != null;
                        String key = data.getString("key");
                        String date = data.getString("date");
                        String pt_name = data.getString("pt_name");
                        String price = data.getString("price");
                        String fasilityEt = data.getString("fasility");
                        String departure = data.getString("departure");

                        String date1 = data.getString("mHomeTextInputDateGo");
                        String inputFrom = data.getString("mInputFrom");
                        String inputTo = data.getString("mInputTo");

                        intent.putExtra("keyFromIntent", key);
                        intent.putExtra("dateFromIntent", date);
                        intent.putExtra("ptNameFromIntent", pt_name);
                        intent.putExtra("priceFromIntent", price);
                        intent.putExtra("facilityFromIntent", fasilityEt);
                        intent.putExtra("departureFromIntent", departure);
                        int numberSeat = data.getInt("numberSeat");
                        int finalPrice = data.getInt("priceTicket");

                        intent.putExtra("mHomeTextInputDateGo", date1);
                        intent.putExtra("mInputFrom", inputFrom);
                        intent.putExtra("mInputTo", inputTo);
                        intent.putExtra("numberSeat", numberSeat);

                        intent.putExtra("priceTicket", finalPrice);
                        intent.putIntegerArrayListExtra("selectedSeats", (ArrayList<Integer>) selectedSeats);

                        intent.putExtra("selectedNumber", passengerPhone);
                        intent.putExtra("passengerName", passengerName);
                        startActivity(intent);
                    }
                }
                private void applyClickEffect (View view){
                    Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
                    clickAnimation.setDuration(100);
                    view.startAnimation(clickAnimation);
                }
            });


        }




}
