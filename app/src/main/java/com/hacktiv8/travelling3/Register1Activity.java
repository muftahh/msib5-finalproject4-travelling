package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register1Activity extends AppCompatActivity {
    EditText register1InputPhone,register1InputName;
    MaterialButton register1BtnRegister;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        auth = FirebaseAuth.getInstance();
        register1InputPhone = findViewById(R.id.register1InputPhone);
        register1BtnRegister = findViewById(R.id.register1BtnRegister);
        register1InputName = findViewById(R.id.register1InputName);

        register1BtnRegister.setOnClickListener(v -> {
            // Mendapatkan nomor telepon dari EditText
            String name = register1InputName.getText().toString().trim();
            String phoneNumber = register1InputPhone.getText().toString().trim();
            String url = "https://hacktiv8-finalproject-default-rtdb.asia-southeast1.firebasedatabase.app/";

            // Validasi untuk nama dan nomor telepon
            if (name.isEmpty()) {
                Toast.makeText(Register1Activity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                Toast.makeText(Register1Activity.this, "Nama hanya boleh diisi oleh alfabet dan dapat menggunakan spasi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (phoneNumber.isEmpty()) {
                Toast.makeText(Register1Activity.this, "Nomor telepon harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!phoneNumber.matches("[0-9]+")) {
                Toast.makeText(Register1Activity.this, "Nomor telepon hanya boleh berformat angka", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mendapatkan UID pengguna saat ini
            String uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();

            // Menyimpan nomor telepon ke Realtime Database
            DatabaseReference userRef = FirebaseDatabase.getInstance(url).getReference().child("users").child(uid).child("profile");
            userRef.child("nohp").setValue(phoneNumber);
            userRef.child("name").setValue(name);

            // Pindah ke Register2Activity (atau aktivitas lainnya)
            Intent intent = new Intent(Register1Activity.this, Register2Activity.class);
            startActivity(intent);
        });

    }
}