package edu.asu.bsse.ajmack5.GeoLoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import logic.JsonHandler;
import logic.PlaceDescription;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

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


        ///creates a new place when the when button is pressed.
        submitButton = (Button)findViewById(R.id.inputLocationBut);
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
                if(!jHandler.placeExists(editName.getText().toString())) {
                    jHandler.addPlace(place);
                }
                else{
                    Toast.makeText(MainActivity.this, "Place name already exists", Toast.LENGTH_SHORT).show();
                }

            };
        });



    }






}
