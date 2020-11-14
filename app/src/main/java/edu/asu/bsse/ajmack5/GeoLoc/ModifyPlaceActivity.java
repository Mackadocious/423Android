package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.jar.JarEntry;

import logic.DatabaseHandler;
import logic.JsonHandler;
import logic.PlaceDescription;


public class ModifyPlaceActivity extends AppCompatActivity {

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
    JsonHandler jHandler;
    String name;
    DatabaseHandler dhandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_place);
        final Intent mainIntent = new Intent(this, MainActivity.class);

        editName = (EditText) findViewById(R.id.editName);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editCategory = (EditText) findViewById(R.id.editCategory);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editStreet = (EditText) findViewById(R.id.editStreet);
        editElevation = (EditText) findViewById(R.id.editElevation);
        editLat = (EditText) findViewById(R.id.editLat);
        editLong = (EditText) findViewById(R.id.editLong);
        Context context = this;
        final String path = context.getFilesDir().toString();
        jHandler = new JsonHandler(path);
        dhandler = new DatabaseHandler(context);


        editName.setEnabled(false);

        updateFields();

        submitButton = (Button)findViewById(R.id.modifyButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("PATH: "+ path);

                PlaceDescription place = new PlaceDescription();
                place.setName(name);
                place.setDescription(editDescription.getText().toString());
                place.setCategory(editCategory.getText().toString());
                place.setAddressTitle(editAddress.getText().toString());
                place.setAddressStreet(editStreet.getText().toString());
                place.setElevation(Double.valueOf(editElevation.getText().toString()));
                place.setLatitude(Double.valueOf(editLat.getText().toString()));
                place.setLongtitude(Double.valueOf(editLong.getText().toString()));
                dhandler.getPlaceLibrary().removePlace(name);
                dhandler.deletePlace(name);
                dhandler.addPlace(place);
                Toast.makeText(ModifyPlaceActivity.this, "Modified place", Toast.LENGTH_SHORT).show();
                startActivity(mainIntent);



            };
        });
    }

    private void updateFields() {
        Intent intent = getIntent();
        name = intent.getExtras().getString("nameOfPlace");
        editName.setText(name);
        PlaceDescription selectedPlace = dhandler.getPlace(name);
        editDescription.setText(selectedPlace.getDescription());
        editCategory.setText(selectedPlace.getCategory());
        editAddress.setText(selectedPlace.getAddressTitle());
        editStreet.setText(selectedPlace.getAddressStreet());
        editElevation.setText(String.valueOf(selectedPlace.getElevation()));
        editLong.setText(String.valueOf(selectedPlace.getLongtitude()));
        editLat.setText(String.valueOf(selectedPlace.getLatitude()));
    }


}
