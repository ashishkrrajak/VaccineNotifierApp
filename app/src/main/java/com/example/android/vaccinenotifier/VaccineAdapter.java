package com.example.android.vaccinenotifier;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class VaccineAdapter extends ArrayAdapter<Vaccine> {

    public VaccineAdapter(@NonNull Context context, ArrayList<Vaccine> vaccine) {
        super(context, 0, vaccine);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list,parent,false);
        }
        Vaccine currentVaccine = getItem(position);

        TextView doseOne = listItemView.findViewById(R.id.vaccineDose1);
        TextView doseTwo = listItemView.findViewById(R.id.vaccineDose2);
        TextView ageLimit = listItemView.findViewById(R.id.ageLimit);
        TextView feeType = listItemView.findViewById(R.id.fees_type);
        TextView centerName = listItemView.findViewById(R.id.vaccineLocation);
        TextView vaccineName = listItemView.findViewById(R.id.vaccineName);
        TextView date = listItemView.findViewById(R.id.vaccineDate);
        // dose one
        doseOne.setText(String.valueOf(currentVaccine.getAvailability1()));
        GradientDrawable doseOneDrawable = (GradientDrawable) doseOne.getBackground();
        doseOneDrawable.setColor(getColorAvailability((currentVaccine.getAvailability1())));
        // dose two
        doseTwo.setText(String.valueOf(currentVaccine.getAvailability2()));
        GradientDrawable doseTwoDrawable = (GradientDrawable) doseTwo.getBackground();
        doseTwoDrawable.setColor(getColorAvailability((currentVaccine.getAvailability2())));
        String ageLimitString = String.valueOf(currentVaccine.getAgeLimit());
        if (currentVaccine.isAllAge()){
            ageLimitString = ageLimitString+"& above";
        }else {
            if (ageLimitString.equals("18"))
            {
                ageLimitString = ageLimitString+"-44";
            }
            else {
                ageLimitString = ageLimitString+"& above";
            }
        }
        ageLimit.setText(ageLimitString);// age limit of vaccine like 18-44 , 18 & above.
        // Fee Type of vaccine
        feeType.setText(currentVaccine.getVaccineFee());
        GradientDrawable drawable = (GradientDrawable) feeType.getBackground();
        drawable.setColor(getPriceColor(currentVaccine.getVaccineFee()));
        // Center name
        centerName.setText(currentVaccine.getLocation());
        // Vaccine Name
        vaccineName.setText(currentVaccine.getVaccineName());
        // Date Of vaccine
        date.setText(currentVaccine.getVaccineDate());



        return listItemView;
    }
    private int getColorAvailability(int dose){
        int colorResourceId;
        if (dose>15) {
            colorResourceId = R.color.available;
        }else if (dose>=1){
            colorResourceId = R.color.lessAvailable;
        }else {
            colorResourceId = R.color.notAvailable;
        }
        return ContextCompat.getColor(getContext(),colorResourceId);
    }

    private int getPriceColor(String price){
        int colorResourceId;
        Log.i("price", "getPriceColor: "+price);
        if (price.equals("Free")){
            colorResourceId = R.color.free;
        }else{
            colorResourceId = R.color.paid;
        }
        return ContextCompat.getColor(getContext(),colorResourceId);
    }
}
