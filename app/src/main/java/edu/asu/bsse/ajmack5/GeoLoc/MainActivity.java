package edu.asu.bsse.ajmack5.GeoLoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

import logic.PlaceDescription;

public class MainActivity extends AppCompatActivity {
    PlaceDescription place;
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


        submitButton = (Button)findViewById(R.id.inputLocationBut);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("PATH: "+ path);

                place = new PlaceDescription(editName.getText().toString(), editDescription.getText().toString(), editCategory.getText().toString(),
                        editAddress.getText().toString(), editStreet.getText().toString(), Double.valueOf(editElevation.getText().toString()),
                        Double.valueOf(editLat.getText().toString()), Double.valueOf(editLong.getText().toString()), path);



            };
        });




    }






}
