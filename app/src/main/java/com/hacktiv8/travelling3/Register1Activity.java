package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register1Activity extends AppCompatActivity {
    EditText register1InputPhone;
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

        register1BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nomor telepon dari EditText
                String phoneNumber = register1InputPhone.getText().toString().trim();
                String url = "https://hacktiv8-finalproject-default-rtdb.asia-southeast1.firebasedatabase.app/";

                // Mendapatkan UID pengguna saat ini
                String uid = auth.getCurrentUser().getUid();

                // Menyimpan nomor telepon ke Realtime Database
                DatabaseReference userRef = FirebaseDatabase.getInstance(url).getReference().child("users").child(uid);
                userRef.child("nohp").setValue(phoneNumber);

                // Pindah ke Register2Activity (atau aktivitas lainnya)
                Intent intent = new Intent(Register1Activity.this, Register2Activity.class);
                startActivity(intent);
            }
        });
    }
}