package com.example.android.vaccinenotifier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PincodeFragment extends Fragment {

    @SuppressLint("SimpleDateFormat")
    private final String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private final String urlPin = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=******&date=" + date;

    public PincodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pincode, container, false);

        EditText pinCode = view.findViewById(R.id.editText);
//        Toast.makeText(getContext(), ""+pinCode.getText().toString(), Toast.LENGTH_SHORT).show();

        Button findButton = view.findViewById(R.id.findButton);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinData = pinCode.getText().toString();
                if (pinData.length()==6) {
                    Intent intent = new Intent(getContext(), VaccineActivity.class);
                    String url = urlPin.replace("******", pinData);
                    intent.putExtra("type", "pin");
                    intent.putExtra("url", url);
                    startActivity(intent);
                }else if(pinData.length()!=0){
                    Toast.makeText(getContext(), R.string.enter_correct_pinCode, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), R.string.enter_pinCode, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}