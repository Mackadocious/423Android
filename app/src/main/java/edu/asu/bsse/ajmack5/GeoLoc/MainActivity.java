package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logic.DatabaseHandler;
import logic.JsonHandler;
import logic.PlaceDescription;

public class MainActivity extends AppCompatActivity {
    JsonHandler jHandler;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String path;
    TextView resultView;
    String selectedItem;


    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler dhandler = new DatabaseHandler(this);
        dhandler.getPlaceLibrary();

        selectedItem = "";
        Context context = this;
        String path = context.getFilesDir().toString();
        final Intent viewPlacesIntent = new Intent(this, placeListActivity.class);
        final Intent addPlaceIntent = new Intent(this, addPlaceActivity.class);
        final Intent modifyPlaceIntent = new Intent(this, ModifyPlaceActivity.class);
        final Intent calcIntent = new Intent(this, CalcActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = (TextView) findViewById(R.id.resultView);


        getSupportActionBar().setTitle("GeoLoc"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jHandler = new JsonHandler(path);

        listView = (ListView) findViewById(R.id.placesListView);
        List<String> namesOfPlaces = new ArrayList<String>();
        for (int i = 0; i < dhandler.getPlaceLibrary().getPlaceLibrary().size(); i++) {
            System.out.println("ADDING");
            namesOfPlaces.add(dhandler.getPlaceLibrary().getPlaceLibrary().get(i).getName());
        }
        Collections.sort(namesOfPlaces);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesOfPlaces);
        listView.setAdapter(arrayAdapter);


        final Button addNewPlace = findViewById(R.id.addNewPlace);
        addNewPlace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(addPlaceIntent);


            }
        });

        final Button modifyPlace = findViewById(R.id.modifyButton);
        modifyPlace.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                modifyPlaceIntent.putExtra("nameOfPlace", selectedItem);
                startActivity(modifyPlaceIntent);
            }
        });

        final Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                startActivity(calcIntent);
            }
        });

        final Button deletePlace = findViewById(R.id.deleteButton);
        deletePlace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dhandler.getPlaceLibrary().removePlace(selectedItem);
                dhandler.deletePlace(selectedItem);


                List<String> namesOfPlaces = new ArrayList<String>();
                for (int i = 0; i < dhandler.getPlaceLibrary().getPlaceLibrary().size(); i++) {
                    System.out.println("ADDING");
                    namesOfPlaces.add(dhandler.getPlaceLibrary().getPlaceLibrary().get(i).getName());
                }
                arrayAdapter.remove(selectedItem);
                arrayAdapter.notifyDataSetChanged();




            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                setResultView(dhandler.getPlace(o.toString()));
                selectedItem  = dhandler.getPlace(o.toString()).getName();


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








