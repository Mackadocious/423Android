package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import logic.JsonHandler;
import logic.PlaceDescription;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        final Intent viewPlacesIntent = new Intent(this, placeListActivity.class);
        final Intent addPlaceIntent = new Intent(this, addPlaceActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button viewPlaces = findViewById(R.id.viewPlaceList);
        viewPlaces.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){

              startActivity(viewPlacesIntent);


            }
        });

        final Button addNewPlace = findViewById(R.id.addNewPlace);
        addNewPlace.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                startActivity(addPlaceIntent);


            }
        });











    }






}

