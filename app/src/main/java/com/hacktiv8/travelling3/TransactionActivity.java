package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Bundle data = getIntent().getExtras();
        assert data != null;
        String keyBus = data.getString("keyBus");
        String ptBus = data.getString("ptBus");
        String username = data.getString("userName");
        String from = data.getString("from");
        String destination = data.getString("destination");
        String date = data.getString("date");
        String time = data.getString("time");
        String seat = data.getString("seat");
        String clas = data.getString("clas");
        int numberSeat = data.getInt("numberSeat");
        int sumSeat = data.getInt("sumSeat");
        int price = data.getInt("price");

        int finalSumSeat = numberSeat - sumSeat;

        String randomData = generateRandomString();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(price);

        String finalPrice = "Rp. "+formattedPrice;

        TextView priceTv = findViewById(R.id.price_tv);
        priceTv.setText(formattedPrice);

        MaterialButton pay = findViewById(R.id.pay_button);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    Date today = calendar.getTime();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = sdf.format(today);

                    auth = FirebaseAuth.getInstance();
                    String uid = auth.getCurrentUser().getUid();
                    String baseUrl = getResources().getString(R.string.base_url);
                    database = FirebaseDatabase.getInstance(baseUrl).getReference("users").child(uid).child("Ticket").push();

                    Map<String, Object> ticketData = new HashMap<>();
                    ticketData.put("asalBis", from);
                    ticketData.put("classBis", clas);
                    ticketData.put("harga", finalPrice);
                    ticketData.put("jamPergi", time);
                    ticketData.put("namaBis", ptBus);
                    ticketData.put("namaPenumpang", username);
                    ticketData.put("noKursi", seat);
                    ticketData.put("tanggalKeberangkatan", date);
                    ticketData.put("tujuanBis", destination);
                    ticketData.put("tanggalPembelian", formattedDate);
                    ticketData.put("nomerBooking", randomData);


                    database.setValue(ticketData);

                      try {
                          ArrayList<Integer> finalSeaat = new ArrayList<>();

                          Pattern pattern = Pattern.compile("\\d+");
                          Matcher matcher = pattern.matcher(seat);

                          while (matcher.find()) {
                              int number = Integer.parseInt(matcher.group());
                              finalSeaat.add(number);
                          }

                          database = FirebaseDatabase.getInstance(baseUrl).getReference("bus_data").child(keyBus).child("taken_seat");
                          for (int num : finalSeaat) {
                              DatabaseReference newSeatRef = database.push();
                              Map<String, Object> seatData = new HashMap<>();
                              seatData.put("number",num);
                              seatData.put("user",username);
                              newSeatRef.setValue(seatData);
                          }


                      }catch (Exception e) {
                          System.out.println("Terjadi kesalahan: " + e.getMessage());
                      }

                      try {
                          database = FirebaseDatabase.getInstance(baseUrl).getReference("bus_data").child(keyBus);
                          Map<String, Object> sumSeatData = new HashMap<>();
                          sumSeatData.put("number_seats", finalSumSeat);
                          database.updateChildren(sumSeatData);
                      }catch (Exception e) {
                          System.out.println("Terjadi kesalahan: " + e.getMessage());
                      }

                    Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    System.out.println("Terjadi kesalahan: " + e.getMessage());
                }
            }


        });

    }

    private String generateRandomString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append((int) (Math.random() * 10));

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < 3; i++) {  // Tambahkan 3 karakter acak
            char c = chars[(int) (Math.random() * chars.length)];
            stringBuilder.append(c);
        }

        stringBuilder.append((int) (Math.random() * 10));

        for (int i = 0; i < 3; i++) {
            char c = chars[(int) (Math.random() * chars.length)];
            stringBuilder.append(c);
        }

        stringBuilder.append((int) (Math.random() * 10));

        return stringBuilder.toString();
    }





}