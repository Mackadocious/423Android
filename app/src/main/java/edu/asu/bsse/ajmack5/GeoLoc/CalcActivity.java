package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import logic.JsonHandler;
import logic.PlaceDescription;
import logic.PlaceLibrary;

public class CalcActivity extends AppCompatActivity {
    Spinner sItems01;
    Spinner sItems02;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calc);
            final Intent mainIntent = new Intent(this, MainActivity.class);

            Context context = this;
            final String path = context.getFilesDir().toString();
            final JsonHandler jhandler = new JsonHandler(path);


            List<PlaceDescription> placeArray = jhandler.getPlaceLibrary().places;
            List<String> spinnerArray = new ArrayList<>();
            for(int i = 0; i < placeArray.size(); i++) {
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
            calcButton.setOnClickListener(new View.OnClickListener(){
                public void onClick (View v){
                   calcDistance(jhandler.getPlace(sItems01.getSelectedItem().toString()), jhandler.getPlace(sItems02.getSelectedItem().toString()));
                   calcBearing(jhandler.getPlace(sItems01.getSelectedItem().toString()), jhandler.getPlace(sItems02.getSelectedItem().toString()));
                }
            });

        }

    private void calcBearing(PlaceDescription place1, PlaceDescription place2) {
    }

    private void calcDistance(PlaceDescription place1, PlaceDescription place2) {

        if ((place1.getLatitude() == place2.getLatitude()) && (place1.getLongtitude() == place2.getLongtitude())) {
            return 0;
        }
		else {
            double theta = place1.getLongtitude() - place2.getLongtitude();
            double dist = Math.sin(Math.toRadians(place1.getLatitude())) * Math.sin(Math.toRadians(place2.getLatitude())) + Math.cos(Math.toRadians(place1.getLatitude())) * Math.cos(Math.toRadians(place2.getLatitude())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }


}
