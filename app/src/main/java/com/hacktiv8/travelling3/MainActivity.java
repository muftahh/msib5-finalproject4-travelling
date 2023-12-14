
package com.hacktiv8.travelling3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageButton homeBtnKembali, homeBtnAccount;
    private Spinner homeInputFrom, homeInputTo;
    private TextView homeTextInputDateGo, homeTextInputDateReturn;
    private ImageButton homeBtnInputDateGo, homeBtnInputDateReturn;
    private MaterialButton homeBtnSearch;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

        homeBtnKembali = findViewById(R.id.homeBtnKembali);
        homeBtnAccount = findViewById(R.id.homeBtnAccount);
        homeInputFrom = findViewById(R.id.homeInputFrom);
        homeInputTo = findViewById(R.id.homeInputTo);
        homeTextInputDateGo = findViewById(R.id.homeTextInputDateGo);
        homeTextInputDateReturn = findViewById(R.id.homeTextInputDateReturn);
        homeBtnInputDateGo = findViewById(R.id.homeBtnInputDateGo);
        homeBtnInputDateReturn = findViewById(R.id.homeBtnInputDateReturn);
        homeBtnSearch = findViewById(R.id.homeBtnSearch);

        homeBtnInputDateGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogGo();
            }
        });
        homeBtnInputDateReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogReturn();
            }
        });

        homeBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTicket();
            }
        });
    }

    private void searchTicket() {
        String mInputFrom = homeInputFrom.getSelectedItem().toString();
        String mInputTo = homeInputTo.getSelectedItem().toString();
        String mHomeTextInputDateGo = homeTextInputDateGo.getText().toString();
        String mHomeTextInputDateReturn = homeTextInputDateReturn.getText().toString();

        //testing
        System.out.println(mHomeTextInputDateReturn);
        System.out.println(mHomeTextInputDateGo);
        System.out.println(mInputFrom);
        System.out.println(mInputTo);

        Intent intent = new Intent(MainActivity.this, ListTicketActivity.class);
        intent.putExtra("mInputFrom", mInputFrom);
        intent.putExtra("mInputTo", mInputTo);
        intent.putExtra("mHomeTextInputDateGo", mHomeTextInputDateGo);
        startActivity(intent);
    }

    private void showDateDialogGo(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                homeTextInputDateGo.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    private void showDateDialogReturn(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                homeTextInputDateReturn.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}