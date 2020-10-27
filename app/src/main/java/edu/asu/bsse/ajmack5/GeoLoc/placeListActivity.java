package edu.asu.bsse.ajmack5.GeoLoc;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import logic.JsonHandler;

public class placeListActivity extends AppCompatActivity {

    JsonHandler jHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = this;
        final String path = context.getFilesDir().toString();
        super.onCreate(savedInstanceState);
        ListView listView;
        setContentView(R.layout.place_list);


        final JsonHandler jHandler = new JsonHandler(path);

        listView = (ListView) findViewById(R.id.placesListView);
        List<String> namesOfPlaces = new ArrayList<String>();
        for (int i = 0; i < jHandler.getPlaceDescriptionList().size(); i++) {
            namesOfPlaces.add(jHandler.getPlaceDescriptionList().get(i).getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesOfPlaces);
        listView.setAdapter(arrayAdapter);
    }

}
