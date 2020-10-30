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

import logic.JsonHandler;
import logic.PlaceDescription;

public class CalcActivity extends AppCompatActivity {
    Spinner sItems01;
    Spinner sItems02;
    String result;
    TextView resultView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);
        final Intent mainIntent = new Intent(this, MainActivity.class);

        resultView = (TextView) findViewById(R.id.resultView);

        Context context = this;
        final String path = context.getFilesDir().toString();
        final JsonHandler jhandler = new JsonHandler(path);


        List<PlaceDescription> placeArray = jhandler.getPlaceLibrary().places;
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
                result = (" Distance: " + calcDistance(jhandler.getPlace(sItems01.getSelectedItem().toString()),
                        jhandler.getPlace(sItems02.getSelectedItem().toString()))+ " Meters\n");
                result += "Bearing: " + calcBearing(jhandler.getPlace(sItems01.getSelectedItem().toString()),
                        jhandler.getPlace(sItems02.getSelectedItem().toString())) + "\n";
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

        double longDiff= place2.getLongtitude()-place1.getLongtitude();
        double y = Math.sin(longDiff)*Math.cos(place2.getLatitude());
        double x = Math.cos(place1.getLatitude())*Math.sin(place2.getLatitude())-Math.sin(place1.getLatitude())*Math.cos(place2.getLatitude())*Math.cos(longDiff);

        return Math.toDegrees((Math.atan2(y, x))+360)%360;
    }




}
