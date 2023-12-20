package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private ImageButton order, back;
    private List<Integer> selectedSeats = new ArrayList<>();

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

        Intent intent = getIntent();
        if (intent != null) {
            selectedSeats = intent.getIntegerArrayListExtra("selectedSeats");
        }



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

                    Spinner spinner = findViewById(R.id.number_spiner);
                    String selectedNumber = spinner.getSelectedItem().toString();

                    EditText passangerName = findViewById(R.id.passager_name);
                    String passengerName = passangerName.getText().toString();

                    EditText passangerAge = findViewById(R.id.passager_age);
                    String passengerAgeStr = passangerAge.getText().toString();
                    int passengerAge = Integer.parseInt(passengerAgeStr);

                    EditText passangerPhone = findViewById(R.id.passager_phone);
                    String passengerPhone = selectedNumber + passangerPhone.getText().toString();



                    RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String gender = selectedRadioButton.getText().toString();

                    Intent intent = new Intent(OrderActivity.this, DetailPaymentActivity.class);

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
                    int finalPrice = data.getInt("priceTicket");

                    intent.putExtra("mHomeTextInputDateGo", date1);
                    intent.putExtra("mInputFrom", inputFrom);
                    intent.putExtra("mInputTo", inputTo);

                    intent.putExtra("priceTicket", finalPrice);
                    intent.putIntegerArrayListExtra("selectedSeats", (ArrayList<Integer>) selectedSeats);

                    intent.putExtra("selectedNumber", passengerPhone);
                    intent.putExtra("passengerName", passengerName);
                    intent.putExtra("passengerAge", passengerAge);
                    intent.putExtra("gender", gender);
                    startActivity(intent);
                }
                private void applyClickEffect (View view){
                    Animation clickAnimation = new AlphaAnimation(1F, 0.7F);
                    clickAnimation.setDuration(100);
                    view.startAnimation(clickAnimation);
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


}
