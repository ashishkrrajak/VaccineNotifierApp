package com.example.android.vaccinenotifier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DistrictFragment extends Fragment {

    @SuppressLint("SimpleDateFormat")
    private final String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private final String urlDistrict = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=***&date=" + date;
    private Spinner stateSpinner, districtSpinner;                  //Spinners
    private ArrayAdapter<String> districtAdapter;   //declare adapters for the spinners
    private String selectedState;
    private int stateId ;
    private String selectedDistrict;
    private int districtId = -1;
    private JSONObject districtObject = new JSONObject();
    private ArrayList<String> districtList = new ArrayList<String>();
    private ProgressBar progressBar;
    private String url = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/";

    public DistrictFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_district, container, false);

        //State Spinner Initialisation
        stateSpinner = view.findViewById(R.id.stateSpinner);    //Finds a view that was identified by the android:id attribute in xml
        //district Spinner Initialisation
        districtSpinner = view.findViewById(R.id.districtSpinner);
        progressBar= view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        //Populate ArrayAdapter using string array and a spinner layout that we will define
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.states, R.layout.layout_spinner);

        districtList.add("Select Your District");
        districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
        districtSpinner.setAdapter(districtAdapter);
        // Specify the layout to use when the list of choices appear
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateSpinner.setAdapter(stateAdapter);            //Set the adapter to the spinner to populate the State Spinner

        //When any item of the stateSpinner uis selected
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selectedState = stateSpinner.getSelectedItem().toString();      //Obtain the selected State

                int parentID = parent.getId();
                if (parentID == R.id.stateSpinner) {
                    switch (selectedState) {
                        case "Andhra Pradesh":
                            stateId = 2;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Arunachal Pradesh":
                            stateId = 3;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Assam":
                            stateId = 4;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Bihar":
                            stateId = 5;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Chhattisgarh":
                            stateId = 7;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Goa":
                            stateId = 10;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Gujarat":
                            stateId = 11;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Haryana":
                            stateId = 12;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Himachal Pradesh":
                            stateId = 13;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Jharkhand":
                            stateId = 15;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Karnataka":
                            stateId = 16;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Kerala":
                            stateId = 17;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Madhya Pradesh":
                            stateId = 20;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Maharashtra":
                            stateId = 21;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Manipur":
                            stateId = 22;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Meghalaya":
                            stateId = 23;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Mizoram":
                            stateId = 24;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Nagaland":
                            stateId = 25;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Odisha":
                            stateId = 26;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Punjab":
                            stateId = 28;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Rajasthan":
                            stateId = 29;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Sikkim":
                            stateId = 30;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Tamil Nadu":
                            stateId = 31;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Telangana":
                            stateId = 32;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Tripura":
                            stateId = 33;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Uttar Pradesh":
                            stateId = 34;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Uttarakhand":
                            stateId = 35;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "West Bengal":
                            stateId = 36;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Andaman and Nicobar Islands":
                            stateId = 1;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Chandigarh":
                            stateId = 6;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Dadra and Nagar Haveli":
                            stateId = 8;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Daman and Diu":
                            stateId = 37;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Delhi":
                            stateId = 9;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Jammu and Kashmir":
                            stateId = 14;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Lakshadweep":
                            stateId = 19;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Ladakh":
                            stateId = 18;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        case "Puducherry":
                            stateId = 27;
                            districtList = refreshList(stateId);
                            districtAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_spinner, districtList);
                            break;
                        default:
                            break;
                    }
                    //Define City Spinner but we will populate the options through the selected state

                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
                    districtSpinner.setAdapter(districtAdapter);        //Populate the list of Districts in respect of the State selected

                    //To obtain the selected District from the spinner
                    districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedDistrict = districtSpinner.getSelectedItem().toString();
//                            Toast.makeText(getContext(), ""+selectedDistrict, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getContext(), "" + selectedDistrict, Toast.LENGTH_SHORT).show();
                            if (selectedDistrict != "Select Your District") {
                                if (districtObject != null) {
                                    try {
                                        districtId = districtObject.getInt(selectedDistrict.toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(getContext(), R.string.error_try_again, Toast.LENGTH_SHORT).show();

                                    }
//                                Toast.makeText(getContext(), ""+districtObject.toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), R.string.object_error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button findButton = view.findViewById(R.id.findButton);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (districtId != -1) {
                    Intent intent = new Intent(getContext(), VaccineActivity.class);
                    String url = urlDistrict.replace("***", String.valueOf(districtId));
                    intent.putExtra("type", "district");
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    if(selectedState.equals(getString(R.string.select_state))){
                        Toast.makeText(getContext(), R.string.select_state, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), R.string.select_district, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return view;
    }

    private ArrayList<String> refreshList(int id) {
        progressBar.setVisibility(View.VISIBLE);
        districtObject = new JSONObject();
        districtList.clear();
        districtList.add("Select Your District");
        getDistrict(url + String.valueOf(stateId));
        return districtList;
    }

    private void getDistrict(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray districtsArray = response.getJSONArray("districts");
                                    for (int i = 0; i < districtsArray.length(); i++) {
                                        JSONObject district = districtsArray.getJSONObject(i);
                                        String name = district.getString("district_name");
                                        int id = district.getInt("district_id");
                                        districtList.add(name);
                                        districtObject.put(name, id);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                progressBar.setVisibility(View.GONE);
//                                Toast.makeText(VaccineActivity.this, "????", Toast.LENGTH_SHORT).show();
//                                vaccineAdapter = new VaccineAdapter(getContext(),  vaccineList);
//                                vaccineListView.setAdapter(vaccineAdapter);

                            }


                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("no Internet", "onErrorResponse: " + error.getMessage());
                                progressBar.setVisibility(View.GONE);
                            }
                        });
        requestQueue.add(jsonObjectRequest);


    }


}