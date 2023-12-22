package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Register2Activity extends AppCompatActivity {
    MaterialButton register2BtnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);


        register2BtnFinish = findViewById(R.id.register2BtnFinish);
        register2BtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = getIntent().getStringExtra("uid");
                Intent intent = new Intent(Register2Activity.this, MainActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });
    }

}