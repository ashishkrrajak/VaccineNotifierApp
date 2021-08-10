package com.example.android.vaccinenotifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VaccineActivity extends AppCompatActivity {
    ListView vaccineListView;
    ArrayList<Vaccine> vaccineList;
    String url, type;
    VaccineAdapter vaccineAdapter;
    TextView test;
    ProgressBar progressBar;
    View customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        customView = findViewById(R.id.customView);
        customView.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        type = intent.getStringExtra("type");

        vaccineListView = findViewById(R.id.vaccineList);
        vaccineList = new ArrayList<>();
        extractVaccines();
//        Toast.makeText(this, ""+vaccineList.isEmpty(), Toast.LENGTH_SHORT).show();
//        if(!vaccineList.isEmpty()){
//            customView.setVisibility(View.GONE);
//        }
//        vaccineAdapter = new VaccineAdapter(this,  vaccineList);
//        vaccineListView.setAdapter(vaccineAdapter);
//        if (type.equals("pin")) {
//            vaccineListView = findViewById(R.id.vaccineList);
//            vaccineList = new ArrayList<>();
//            extractVaccines();
////        vaccineAdapter = new VaccineAdapter(this,  vaccineList);
////        vaccineListView.setAdapter(vaccineAdapter);
//        } else if (type.equals("district")) {
//            progressBar.setVisibility(View.GONE);
//            TextView emptyText = findViewById(R.id.emptyText);
//            ImageView emptyImage = findViewById(R.id.imageView);
//            emptyImage.setImageResource(R.drawable.oops);
//            emptyText.setText(R.string.underConstruction);
//            Toast.makeText(this, "Working on It..", Toast.LENGTH_LONG).show();
//            customView.setVisibility(View.VISIBLE);
//        }


    }

    private void extractVaccines() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        url,
                        null,
                        response -> {
//                        test.setText("Response: " + response.toString());
                            try {
                                JSONArray centersArray = response.getJSONArray("centers");
//                                Toast.makeText(this, ""+centersArray.length(), Toast.LENGTH_SHORT).show();
                                if (centersArray.length() == 0) {
                                    customView.setVisibility(View.VISIBLE);
                                } else {
                                    for (int i = 0; i < centersArray.length(); i++) {
                                        JSONObject currentCenter = centersArray.getJSONObject(i);
                                        String centerName;
                                        try {
                                             centerName= currentCenter.getString("name");
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                            centerName = "Not Available";
                                        }
                                        String vaccinePrice;
                                        try {
                                           vaccinePrice =currentCenter.getString("fee_type");
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                            vaccinePrice ="Not Available";
                                        }

                                        JSONArray sessionsArray = currentCenter.getJSONArray("sessions");
                                        for (int j = 0; j < sessionsArray.length(); j++) {
                                            JSONObject currentSession= sessionsArray.getJSONObject(j);

                                            String vaccineName;
                                            vaccineName= currentSession.getString("vaccine");
                                            int ageLimit ;
                                            try {
                                                ageLimit = currentSession.getInt("min_age_limit");
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                                ageLimit=18;
                                            }
                                            boolean allAge ;
                                            try {
                                                allAge = currentSession.getBoolean("allow_all_age");
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                                allAge = false;
                                            }
                                            int doseOne ;
                                            try {
                                                doseOne = currentSession.getInt("available_capacity_dose1");
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                                doseOne=0;
                                            }
                                            int doseTwo;
                                            try {
                                                doseTwo = currentSession.getInt("available_capacity_dose2");
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                                doseTwo=0;
                                            }
                                            String date ;
                                            try {
                                                date = currentSession.getString("date");
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                                date="";
                                            }
                                            try {
                                                int max_age_limit = currentSession.getInt("max_age_limit");
                                                if(max_age_limit==44){
                                                    allAge=false;
                                                }else if (max_age_limit>44){
                                                    allAge=true;
                                                }
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                            }

//                                                if (i == 0 && j==0) {
//                                                    test.setText(centerName + " " + vaccinePrice+" "+allAge+" "+doseOne+" "+vaccineName);
//                                                }
                                            vaccineList.add(new Vaccine(doseOne, doseTwo, centerName, date, vaccinePrice, vaccineName, ageLimit, allAge));
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "Error! ", Toast.LENGTH_SHORT).show();
                                if (vaccineList.isEmpty()) {
                                    customView.setVisibility(View.VISIBLE);
                                }
                            }
                            progressBar.setVisibility(View.GONE);
//                                Toast.makeText(VaccineActivity.this, "????", Toast.LENGTH_SHORT).show();
                            vaccineAdapter = new VaccineAdapter(VaccineActivity.this, vaccineList);
                            vaccineListView.setAdapter(vaccineAdapter);

                        },
                        error -> {
                            Log.d("no Internet", "onErrorResponse: " + error.getMessage());
                            Toast.makeText(this, "Internet Problem!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            TextView noInternetText = findViewById(R.id.emptyText);
                            noInternetText.setText(R.string.noInternet);
                            customView.setVisibility(View.VISIBLE);

                        });
        requestQueue.add(jsonObjectRequest);


    }

}