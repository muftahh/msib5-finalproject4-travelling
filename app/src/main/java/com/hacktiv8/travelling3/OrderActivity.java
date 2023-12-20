package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class OrderActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private ImageButton order, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Spinner spinner = (Spinner) findViewById(R.id.number_spiner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        radioGroup = findViewById(R.id.rg_gender);
        order = findViewById(R.id.order_bt);
        back = findViewById(R.id.back_bt);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, ScheduleBusActivity.class);

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
                intent.putExtra("keyFromIntent", key);
                intent.putExtra("dateFromIntent", date);
                intent.putExtra("ptNameFromIntent", pt_name);
                intent.putExtra("priceFromIntent", price);
                intent.putExtra("facilityFromIntent", fasilityEt);
                intent.putExtra("departureFromIntent", departure);

                intent.putExtra("mHomeTextInputDateGo", date1);
                intent.putExtra("mInputFrom", inputFrom);
                intent.putExtra("mInputTo", inputTo);

                startActivity(intent);

            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyClickEffect(order);
                Intent intent = new Intent(OrderActivity.this, DetailPaymentActivity.class);
                startActivity(intent);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = findViewById(checkedId);

                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) group.getChildAt(i);
                    radioButton.setChecked(radioButton == checkedRadioButton);
                }
            }
        });

    }

    private void applyClickEffect(View view) {
        Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
        clickAnimation.setDuration(100);
        view.startAnimation(clickAnimation);
    }
}