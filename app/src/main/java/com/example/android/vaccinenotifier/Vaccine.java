package com.example.android.vaccinenotifier;

public class Vaccine {
    private String vaccineLocation;

    private int vaccineAvailability1;

    private int vaccineAvailability2;

    private String vaccineDate;

    private String vaccineFee;

    private String vaccineName;

    private int agelimit;

    private boolean allAge;


    public Vaccine(int Availability, int Availability2, String Location, String Date, String Fee, String VaccineName, int AgeLimit, boolean AllAge){
        vaccineAvailability1 = Availability;
        vaccineAvailability2 = Availability2;
        vaccineLocation = Location;
        vaccineDate = Date;
        vaccineFee = Fee;
        agelimit = AgeLimit;
        vaccineName = VaccineName;
        allAge = AllAge;
    }
    public String getLocation(){
        return vaccineLocation;
    }
    public int getAvailability1(){
        return vaccineAvailability1;
    }
    public int getAvailability2(){
        return vaccineAvailability2;
    }
    public String getVaccineDate(){
        return vaccineDate;
    }
    public String getVaccineFee(){
        return vaccineFee;
    }
    public String getVaccineName(){
        return vaccineName;
    }
    public int getAgeLimit(){
        return agelimit;
    }
    public boolean isAllAge() {
        return allAge;
    }
}
