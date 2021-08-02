package com.example.android.vaccinenotifier;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("SimpleDateFormat")
    private final String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private String url;            //declaring TextView to show the errors
    private final String urlPin ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=******&date="+date;

    private EditText pinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
    }
}