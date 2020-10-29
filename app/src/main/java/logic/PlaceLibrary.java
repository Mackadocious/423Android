package logic;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaceLibrary implements Serializable {

    public List<PlaceDescription> places = new ArrayList<>();

    public List<PlaceDescription> getPlaceLibrary(){
        return places;

    }

    public PlaceDescription getPlace(String placeName){
        PlaceDescription resultPlace = null;
        for(int i = 0; i < places.size(); i++){
            if(places.get(i).name.equalsIgnoreCase(placeName)){
                resultPlace = places.get(i);


            }
        }
        return resultPlace;
    }

    public void addPlace(PlaceDescription newPlace){
        places.add(newPlace);
    }

    public void removePlace(String nameOfPlace){
        for(int i =0; i < places.size(); i++){
            if(places.get(i).getName().equalsIgnoreCase(nameOfPlace)){
                places.remove(i);
            }
        }
    }


}


