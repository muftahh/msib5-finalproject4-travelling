package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class Register1Activity extends AppCompatActivity {
    EditText register1InputPhone, register1InputEmail, register1InputPassword;
    MaterialButton register1BtnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        register1InputPhone = findViewById(R.id.register1InputPhone);
        register1InputEmail = findViewById(R.id.register1InputEmail);
        register1InputPassword = findViewById(R.id.register1InputPassword);
        register1BtnRegister = findViewById(R.id.register1BtnRegister);

        register1BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register1Activity.this, Register2Activity.class);
                startActivity(intent);
            }
        });
    }
}