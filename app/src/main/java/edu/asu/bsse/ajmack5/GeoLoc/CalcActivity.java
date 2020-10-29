package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import logic.JsonHandler;
import logic.PlaceDescription;
import logic.PlaceLibrary;

public class CalcActivity extends AppCompatActivity {

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calc);
            final Intent mainIntent = new Intent(this, MainActivity.class);

            Context context = this;
            final String path = context.getFilesDir().toString();
            JsonHandler jhandler = new JsonHandler(path);


            List<PlaceDescription> placeArray = jhandler.getPlaceLibrary().places;
            List<String> spinnerArray = new ArrayList<>();
            for(int i = 0; i < spinnerArray.size(); i++) {
                spinnerArray.add(placeArray.get(i).getName());
                System.out.println(placeArray.get(i).getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner sItems01 = (Spinner) findViewById(R.id.spinner01);
            Spinner sItems02 = (Spinner) findViewById(R.id.spinner02);
            sItems01.setAdapter(adapter);
            sItems02.setAdapter(adapter);

        }
}