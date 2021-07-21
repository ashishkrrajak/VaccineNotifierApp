package com.example.android.vaccinenotifier;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.Toolbar;

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
    private String selectedState;
    private String selectedDistrict;
    private final String dataDistrict ="https://cdn-api.co-vin.in/api/v2/admin/location/districts/";
    private final String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private String url;            //declaring TextView to show the errors
    private final String urlPin ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=******&date="+date;
    private final String urlDistrict= "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=***&date="+date;
    private Spinner stateSpinner, districtSpinner;                  //Spinners
    private ArrayAdapter<CharSequence> districtAdapter;   //declare adapters for the spinners
    private EditText pinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        //State Spinner Initialisation
        stateSpinner = findViewById(R.id.stateSpinner);    //Finds a view that was identified by the android:id attribute in xml

        //Populate ArrayAdapter using string array and a spinner layout that we will define
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.states, R.layout.layout_spinner);

        // Specify the layout to use when the list of choices appear
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateSpinner.setAdapter(stateAdapter);            //Set the adapter to the spinner to populate the State Spinner

        //When any item of the stateSpinner uis selected
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Define City Spinner but we will populate the options through the selected state
                districtSpinner = findViewById(R.id.districtSpinner);

                selectedState = stateSpinner.getSelectedItem().toString();      //Obtain the selected State

                int parentID = parent.getId();
                if (parentID == R.id.stateSpinner){
                    switch (selectedState){
                        case "Select Your State": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.select_your_state, R.layout.layout_spinner);
                            break;
                        case "Andhra Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.andhra_pradesh_districts, R.layout.layout_spinner);
                            break;
                        case "Arunachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.arunachal_pradesh_districts, R.layout.layout_spinner);
                            break;
                        case "Assam": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.assam_districts, R.layout.layout_spinner);
                            break;
                        case "Bihar": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.bihar_districts, R.layout.layout_spinner);
                            break;
                        case "Chhattisgarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.chhattisgarh_districts, R.layout.layout_spinner);
                            break;
                        case "Goa": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.goa_districts, R.layout.layout_spinner);
                            break;
                        case "Gujarat": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.gujarat_districts, R.layout.layout_spinner);
                            break;
                        case "Haryana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.haryana_districts, R.layout.layout_spinner);
                            break;
                        case "Himachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.himachal_pradesh_districts, R.layout.layout_spinner);
                            break;
                        case "Jharkhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.jharkhand_districts, R.layout.layout_spinner);
                            break;
                        case "Karnataka": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.karnataka_districts, R.layout.layout_spinner);
                            break;
                        case "Kerala": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.kerala_districts, R.layout.layout_spinner);
                            break;
                        case "Madhya Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.madhya_pradesh_districts, R.layout.layout_spinner);
                            break;
                        case "Maharashtra": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.maharashtra_districts, R.layout.layout_spinner);
                            break;
                        case "Manipur": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.manipur_districts, R.layout.layout_spinner);
                            break;
                        case "Meghalaya": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.meghalaya_districts, R.layout.layout_spinner);
                            break;
                        case "Mizoram": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.mizoram_districts, R.layout.layout_spinner);
                            break;
                        case "Nagaland": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.nagaland_districts, R.layout.layout_spinner);
                            break;
                        case "Odisha": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.odisha_districts, R.layout.layout_spinner);
                            break;
                        case "Punjab": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.punjab_districts, R.layout.layout_spinner);
                            break;
                        case "Rajasthan": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.rajasthan_districts, R.layout.layout_spinner);
                            break;
                        case "Sikkim": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.sikkim_districts, R.layout.layout_spinner);
                            break;
                        case "Tamil Nadu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.tamil_nadu_districts, R.layout.layout_spinner);
                            break;
                        case "Telangana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.telangana_districts, R.layout.layout_spinner);
                            break;
                        case "Tripura": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.tripura_districts, R.layout.layout_spinner);
                            break;
                        case "Uttar Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.uttar_pradesh_districts, R.layout.layout_spinner);
                            break;
                        case "Uttarakhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.uttarakhand_districts, R.layout.layout_spinner);
                            break;
                        case "West Bengal": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.west_bengal_districts, R.layout.layout_spinner);
                            break;
                        case "Andaman and Nicobar Islands": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.andaman_nicobar_districts, R.layout.layout_spinner);
                            break;
                        case "Chandigarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.chandigarh_districts, R.layout.layout_spinner);
                            break;
                        case "Dadra and Nagar Haveli": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.dadra_nagar_haveli_districts, R.layout.layout_spinner);
                            break;
                        case "Daman and Diu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.daman_diu_districts, R.layout.layout_spinner);
                            break;
                        case "Delhi": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.delhi_districts, R.layout.layout_spinner);
                            break;
                        case "Jammu and Kashmir": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.jammu_kashmir_districts, R.layout.layout_spinner);
                            break;
                        case "Lakshadweep": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.lakshadweep_districts, R.layout.layout_spinner);
                            break;
                        case "Ladakh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.ladakh_districts, R.layout.layout_spinner);
                            break;
                        case "Puducherry": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.puducherry_districts, R.layout.layout_spinner);
                            break;
                        default:  break;
                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
                    districtSpinner.setAdapter(districtAdapter);        //Populate the list of Districts in respect of the State selected

                    //To obtain the selected District from the spinner
                    districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedDistrict = districtSpinner.getSelectedItem().toString();
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
        //To display the selected State and District
        Button findButton = findViewById(R.id.findButton);
        pinCode = findViewById(R.id.editText);
        findButton.setOnClickListener(v -> {

            Intent vaccineIntent = new Intent(this, VaccineActivity.class);

            if (pinCode.getText().toString().isEmpty()){
                if (selectedState.equals("Select Your State")){
                    Toast.makeText(this, "Please select your State from the list", Toast.LENGTH_SHORT).show();
                }else if (selectedDistrict.equals("Select Your District")) {
                    Toast.makeText(MainActivity.this, "Please select your district from the list", Toast.LENGTH_LONG).show();
                }else {

                    url = urlDistrict.replace("***","112");
                    vaccineIntent.putExtra("type","district");
                    vaccineIntent.putExtra("url",url);

                    startActivity(vaccineIntent);
                }
//                Toast.makeText(MainActivity.this, "null"+date, Toast.LENGTH_LONG).show();
            }else{
                if (pinCode.getText().toString().length()!=6 ){
                    Toast.makeText(this, "enter correct pin", Toast.LENGTH_SHORT).show();
                }else {
                    String pin = pinCode.getText().toString();
                    url = urlPin.replace("******", pin);
                    vaccineIntent.putExtra("type", "pin");
                    vaccineIntent.putExtra("url",url);
                    startActivity(vaccineIntent);
                }
            }

        });





    }


    @Override
    protected void onPostResume() {
        pinCode.setText("");
        selectedState = "Select Your State";
        super.onPostResume();
    }
}