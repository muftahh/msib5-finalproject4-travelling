package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DetailPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);

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