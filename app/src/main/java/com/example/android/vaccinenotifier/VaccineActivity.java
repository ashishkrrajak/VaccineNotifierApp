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
    String url,type;
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



        if (type.equals("pin")){
            vaccineListView = findViewById(R.id.vaccineList);
            vaccineList = new ArrayList<>();
            extractVaccines();
//        vaccineAdapter = new VaccineAdapter(this,  vaccineList);
//        vaccineListView.setAdapter(vaccineAdapter);
        }else if (type.equals("district")){
            progressBar.setVisibility(View.GONE);
            TextView emptyText = findViewById(R.id.emptyText);
            ImageView emptyImage = findViewById(R.id.imageView);
            emptyImage.setImageResource(R.drawable.oops);
            emptyText.setText("Under Construction");
            Toast.makeText(this, "Working on It..", Toast.LENGTH_LONG).show();
            customView.setVisibility(View.VISIBLE);
        }



    }

    private void extractVaccines() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
//                        test.setText("Response: " + response.toString());
                                try {
                                    JSONArray centersArray = response.getJSONArray("centers");
                                    for (int i = 0; i < centersArray.length(); i++) {
                                        JSONObject currentCenter = centersArray.getJSONObject(i);
                                        String centerName = currentCenter.getString("name");
                                        String vaccinePrice = currentCenter.getString("fee_type");
                                        JSONArray sessionsArray = currentCenter.getJSONArray("sessions");
                                        for (int j = 0; j < sessionsArray.length(); j++) {
                                            JSONObject currentSession = sessionsArray.getJSONObject(j);
                                            String vaccineName = currentSession.getString("vaccine");
                                            int ageLimit = currentSession.getInt("min_age_limit");
                                            boolean allAge = currentSession.getBoolean("allow_all_age");
                                            int doseOne = currentSession.getInt("available_capacity_dose1");
                                            int doseTwo = currentSession.getInt("available_capacity_dose2");
                                            String date = currentSession.getString("date");
//                                    if (i == 0 && j==0) {
//                                        test.setText(centerName + " " + vaccinePrice+" "+allAge+" "+doseOne+" "+vaccineName);
//                                    }
                                            vaccineList.add(new Vaccine(doseOne,doseTwo,centerName,date,vaccinePrice,vaccineName,ageLimit,allAge));
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                progressBar.setVisibility(View.GONE);
//                                Toast.makeText(VaccineActivity.this, "????", Toast.LENGTH_SHORT).show();
                                vaccineAdapter = new VaccineAdapter(VaccineActivity.this,  vaccineList);
                                vaccineListView.setAdapter(vaccineAdapter);

                            }


                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("no Internet","onErrorResponse: "+error.getMessage());
                                progressBar.setVisibility(View.GONE);
                                TextView noInternetText = findViewById(R.id.emptyText);
                                noInternetText.setText(R.string.noInternet);
                                customView.setVisibility(View.VISIBLE);

                            }
                        });
        requestQueue.add(jsonObjectRequest);


    }


}