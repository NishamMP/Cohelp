package com.example.cohelp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Declare_Int extends AppCompatActivity {

    EditText name, location, veichle_name, veichle_no, address, need, withme, phon;
    Button Check, Gotim, Backtim;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("DECLARATION");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#018786")));
        setContentView(R.layout.activity_declare__int);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        veichle_name = findViewById(R.id.veichle_name);
        veichle_no = findViewById(R.id.veichle_no);
        address = findViewById(R.id.address);
        need = findViewById(R.id.need);
        withme = findViewById(R.id.with_me);
        phon = findViewById(R.id.Phone);
        Check = findViewById(R.id.btn);
        Gotim = findViewById(R.id.gotim);
        Backtim = findViewById(R.id.backtim);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        Gotim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(Gotim);
            }
        });
        Backtim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(Backtim);
            }
        });


        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR NAME", Toast.LENGTH_SHORT).show();
                } else if (location.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR LOCATION", Toast.LENGTH_SHORT).show();
                } else if (veichle_name.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR VEICHLE NAME", Toast.LENGTH_SHORT).show();
                } else if (veichle_no.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR VEICHLE NO", Toast.LENGTH_SHORT).show();
                } else if (address.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR ADDRESS", Toast.LENGTH_SHORT).show();
                } else if (need.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR PURPOSE", Toast.LENGTH_SHORT).show();
                } else if (phon.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER YOUR PHONE NO", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Declare_Int.this, Web.class);
                    intent.putExtra("1Name", name.getText().toString());
                    intent.putExtra("2Loca", location.getText().toString());
                    intent.putExtra("3Veich_Na", veichle_name.getText().toString());
                    intent.putExtra("4Veich_No", veichle_no.getText().toString());
                    intent.putExtra("5Add", address.getText().toString());
                    intent.putExtra("6Need", need.getText().toString());
                    intent.putExtra("7With", withme.getText().toString());
                    intent.putExtra("8Phon", phon.getText().toString());
                    intent.putExtra("go", Gotim.getText().toString());
                    intent.putExtra("went", Backtim.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }

    private void showDateTimeDialog(final Button date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(Declare_Int.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

        new DatePickerDialog(Declare_Int.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}