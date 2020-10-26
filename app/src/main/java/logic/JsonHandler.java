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


    public JsonHandler(String name, String description, String category, String addressTitle,
                       String addressStreet, double elevation, double latitude, double longtitude, String path) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.addressTitle = addressTitle;
        this.addressStreet = addressStreet;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longtitude = longtitude;
        jsonObject = new JSONObject();
        this.path = path;

        try {
            jsonObject.put("name", name);
            jsonObject.put("description", description);
            jsonObject.put("category", category);
            jsonObject.put("address-title", addressTitle);
            jsonObject.put("address-street", addressStreet);
            jsonObject.put("elevation", elevation);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longtitude);
            try {
                writeJson(jsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void writeJson(JSONObject jsonObject) throws IOException, JSONException {
        Writer output = null;
        File file = new File(path + "/info.json");
        output = new BufferedWriter(new FileWriter(file));
        output.write(jsonObject.toString());
        output.close();



    }

    public void readJSON() throws IOException, JSONException {
        System.out.println("PATH: " + path);
        FileReader fileReader = new FileReader(path+ "/info.json");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String input = null;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(input != null){
            stringBuilder.append(input).append("\n");
            input = bufferedReader.readLine();
            System.out.println("OUTPUT: " + input);

        }
        bufferedReader.close();
        String result = stringBuilder.toString();

        JSONArray readObject = new JSONArray(result);

        for(int i = 0; i < readObject.length(); i++){
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

            System.out.println(place.getName());
        }
    }




}
