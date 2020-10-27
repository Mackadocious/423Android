package logic;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.*;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import logic.PlaceDescription;


public class JsonHandler {
    String name, description, category, addressTitle,
    addressStreet;
    double elevation,  latitude,  longtitude;
    JSONObject jsonObject;
    String path;
    List<PlaceDescription> places = new ArrayList<>();


// takes parameter for file path of app
    public JsonHandler(String pathToApplicationFileFolder){
        path = pathToApplicationFileFolder;
        try {
            readJSON();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
//reads entire list of places and creates and returns a json array of all places
    private JSONArray createJSONSArrayFromPlaces() throws JSONException {
        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i < places.size(); i++){
            PlaceDescription currentPlaces = places.get(i);
            JSONObject jo = new JSONObject();
            jo.put("name", currentPlaces.name);
            jo.put("description", currentPlaces.description);
            jo.put("category", currentPlaces.category);
            jo.put("address-street", currentPlaces.addressStreet);
            jo.put("address-title", currentPlaces.addressTitle);
            jo.put("longitude", currentPlaces.longtitude);
            jo.put("latitude", currentPlaces.latitude);
            jo.put("elevation", currentPlaces.elevation);
            jsonArray.put(jo);

        }
        return jsonArray;

    }

//calls the createJSONArrayFromPlaces functions, creates a
    //json array from all places, and writes it to the path passed
    //to the constructor.
    public void writeJson() throws IOException, JSONException {
        Writer output = null;
        File file = new File(path + "/info.json");
        output = new BufferedWriter(new FileWriter(file));
        output.write(createJSONSArrayFromPlaces().toString());
        output.close();



    }
//reads json array from the path passed to the constructor and creates
    //places objects.
    public void readJSON() throws IOException, JSONException {
        System.out.println("PATH: " + path);
        if (new File(path+"/info.json").exists()) {
            FileReader fileReader = new FileReader(path + "/info.json");

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String input = null;
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (input != null) {
                stringBuilder.append(input).append("\n");
                input = bufferedReader.readLine();
                System.out.println("OUTPUT: " + input);

            }
            bufferedReader.close();
            String result = stringBuilder.toString();
            if (result != null) {

                JSONArray readObject = new JSONArray(result);

                for (int i = 0; i < readObject.length(); i++) {

                    JSONObject temp = readObject.getJSONObject(i);

                    PlaceDescription place = new PlaceDescription();
                    place.setAddressStreet(temp.getString("address-street"));
                    place.setAddressTitle(temp.getString("address-title"));
                    place.setCategory(temp.getString("category"));
                    place.setDescription(temp.getString("description"));
                    place.setElevation(temp.getDouble("elevation"));
                    place.setLatitude(temp.getDouble("latitude"));
                    place.setLongtitude(temp.getDouble("longitude"));
                    place.setName(temp.getString("name"));
                    places.add(place);

                    System.out.println(place.getName());
                }
            }
        }
    }
//adds a place to the placedescription list
    public void addPlace(PlaceDescription place){
        places.add(place);
        try {
            writeJson();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean placeExists(String name) {
        for (int i = 0; i < places.size(); i++) {
            System.out.println("PLACE: " + places.size());
            if (places.get(i).getName().equalsIgnoreCase(name)) {

                return true;
            } else {


            }
        }
        return false;
    }
}
