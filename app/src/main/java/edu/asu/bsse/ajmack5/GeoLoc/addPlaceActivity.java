package edu.asu.bsse.ajmack5.GeoLoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import logic.DatabaseHandler;
import logic.JsonHandler;
import logic.PlaceDescription;
import logic.PlaceLibrary;

public class addPlaceActivity extends AppCompatActivity {

    EditText editName;
    EditText editDescription;
    EditText editCategory;
    EditText editAddress;
    EditText editStreet;
    EditText editElevation;
    EditText editLat;
    EditText editLong;
    Button submitButton;
    Context context;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_place);
        final Intent mainIntent = new Intent(this, MainActivity.class);

        editName = (EditText)findViewById(R.id.editName);
        editDescription = (EditText)findViewById(R.id.editDescription);
        editCategory = (EditText)findViewById(R.id.editCategory);
        editAddress = (EditText)findViewById(R.id.editAddress);
        editStreet = (EditText)findViewById(R.id.editStreet);
        editElevation = (EditText)findViewById(R.id.editElevation);
        editLat = (EditText)findViewById(R.id.editLat);
        editLong = (EditText)findViewById(R.id.editLong);
        Context context = this;
        final String path = context.getFilesDir().toString();
        final JsonHandler jHandler = new JsonHandler(path);
        final DatabaseHandler dhandler = new DatabaseHandler(context);


        ///creates a new place when the when button is pressed.
        submitButton = (Button)findViewById(R.id.modifyButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("PATH: "+ path);

                PlaceDescription place = new PlaceDescription();
                place.setName(editName.getText().toString());
                place.setDescription(editDescription.getText().toString());
                place.setCategory(editCategory.getText().toString());
                place.setAddressTitle(editAddress.getText().toString());
                place.setAddressStreet(editStreet.getText().toString());
                place.setElevation(Double.valueOf(editElevation.getText().toString()));
                place.setLatitude(Double.valueOf(editLat.getText().toString()));
                place.setLongtitude(Double.valueOf(editLong.getText().toString()));
                PlaceLibrary places = dhandler.getPlaceLibrary();
                if(!places.placeExists(editName.getText().toString())) {
                    dhandler.addPlace(place);
                    startActivity(mainIntent);
                    Toast.makeText(addPlaceActivity.this, "Added place to library", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(addPlaceActivity.this, "Place name already exists", Toast.LENGTH_SHORT).show();
                }

            };
        });



    }






}