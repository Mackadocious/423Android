package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import logic.JsonHandler;
import logic.PlaceDescription;

public class MainActivity extends AppCompatActivity {
    JsonHandler jHandler;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String path;
    TextView resultView;


    protected void onCreate(Bundle savedInstanceState) {
        Context context = this;
        String path = context.getFilesDir().toString();
        final Intent viewPlacesIntent = new Intent(this, placeListActivity.class);
        final Intent addPlaceIntent = new Intent(this, addPlaceActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = (TextView) findViewById(R.id.resultView);

        getSupportActionBar().setTitle("GeoLoc"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jHandler = new JsonHandler(path);

        listView = (ListView) findViewById(R.id.placesListView);
        List<String> namesOfPlaces = new ArrayList<String>();
        for (int i = 0; i < jHandler.getPlaceDescriptionList().size(); i++) {
            System.out.println("ADDING");
            namesOfPlaces.add(jHandler.getPlaceDescriptionList().get(i).getName());
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesOfPlaces);
        listView.setAdapter(arrayAdapter);


        final Button addNewPlace = findViewById(R.id.addNewPlace);
        addNewPlace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(addPlaceIntent);


            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                setResultView(jHandler.getPlace(o.toString()));
            }


        });




    }


    private void setResultView(PlaceDescription place) {
        String result = ("name: + " + place.getName() + "\ndescription: " + place.getDescription() + "\ncategory: " + place.getCategory() +
                "\naddress-title: " + place.getAddressTitle() + "\naddress-street: " + place.getAddressStreet() + "\nelevation: " + place.getElevation()
                + "\nlatitude: " + place.getLatitude() + "\nlongitude: " + place.getLongtitude());
        resultView.setText(result);


    }









    }








