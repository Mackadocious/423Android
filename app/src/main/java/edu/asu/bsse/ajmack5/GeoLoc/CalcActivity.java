

/**
 *
 * Copyright 2020 Alexander Mack
 *
 *This Item is protected by copyright and/or related rights.
 * You are free to use this Item in any way.
 * @Author Alexander Mack
 * mailto: ajmack5@asu.edu
 * @version Nov 23, 2020
 *
 * @Description: Activity that calculates the distance between locations
 *
 */


package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import logic.DatabaseHandler;
import logic.JsonHandler;
import logic.PlaceDescription;

public class CalcActivity extends AppCompatActivity {
    Spinner sItems01;
    Spinner sItems02;
    String result;
    TextView resultView;
    DatabaseHandler dhandler;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);
        final Intent mainIntent = new Intent(this, MainActivity.class);

        resultView = (TextView) findViewById(R.id.resultView);

        Context context = this;
        final String path = context.getFilesDir().toString();
        final JsonHandler jhandler = new JsonHandler(path);
        dhandler = new DatabaseHandler(context);


        List<PlaceDescription> placeArray = dhandler.getPlaceLibrary().places;
        List<String> spinnerArray = new ArrayList<>();
        for (int i = 0; i < placeArray.size(); i++) {
            spinnerArray.add(placeArray.get(i).getName());
            System.out.println(placeArray.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems01 = (Spinner) findViewById(R.id.spinner01);
        sItems02 = (Spinner) findViewById(R.id.spinner02);
        sItems01.setAdapter(adapter);
        sItems02.setAdapter(adapter);

        final Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result = (" Distance: " + calcDistance(dhandler.getPlace(sItems01.getSelectedItem().toString()),
                        dhandler.getPlace(sItems02.getSelectedItem().toString()))+ " Meters\n");
                result += "Bearing: " + calcBearing(dhandler.getPlace(sItems01.getSelectedItem().toString()),
                        dhandler.getPlace(sItems02.getSelectedItem().toString())) + "\n";
                        result += "Great Circle: " + calcGreatCircle(dhandler.getPlace(sItems01.getSelectedItem().toString()),
                                dhandler.getPlace(sItems02.getSelectedItem().toString())) + " NM \n";
                setResult();
            }
        });

    }

    private void setResult() {
        resultView.setText(result);

    }


    private double calcDistance(PlaceDescription place1, PlaceDescription place2) {
        System.out.println("Place 1 Lat: " + place1.getLatitude() + " place 1 long: " + place1.getLongtitude());
        System.out.println("Place 2 Lat: " + place2.getLatitude() + " place 2 long: " + place2.getLongtitude());



        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(place2.getLatitude() - place1.getLatitude());
        double lonDistance = Math.toRadians(place2.getLongtitude() - place1.getLongtitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(place1.getLatitude())) * Math.cos(Math.toRadians(place2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = place1.getElevation() - place2.getElevation();

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    double calcBearing(PlaceDescription place1, PlaceDescription place2){
        double lat1 = place1.getLatitude();
        double long1 = place1.getLongtitude();
        double lat2 = place2.getLatitude();
        double long2 = place2.getLongtitude();

        double longitude1 = long1;
        double longitude2 = long2;
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double longDiff= Math.toRadians(longitude2-longitude1);
        double y= Math.sin(longDiff)*Math.cos(latitude2);
        double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x))+360)%360;


    }

    double calcGreatCircle(PlaceDescription place1, PlaceDescription place2){

        double lat1 = Math.toRadians(place1.getLatitude());
        double long1 = Math.toRadians(place1.getLongtitude());
        double lat2 = Math.toRadians(place2.getLatitude());
        double long2 = Math.toRadians(place2.getLongtitude());

        double angle1 = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2));

        // convert back to degrees
        angle1 = Math.toDegrees(angle1);

        // each degree on a great circle of Earth is 60 nautical miles
        double distance1 = 60 * angle1;

        return distance1;

    }




}
