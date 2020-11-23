

/**
 *
 * Copyright 2020 Alexander Mack
 *
 *This Item is protected by copyright and/or related rights.
 * You are free to use this Item in any way.
 * @Author Alexander Mack
 * mailto: ajmack5@asu.edu
 * @version Nov 23, 2020
 *
 * @Description: The place library stores a list of the individual place descriptions. It provides getters and setters
 * for the various places that are held within the place library.
 *
 */


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

    public boolean placeExists(String name){
     for(int i = 0 ; i < places.size(); i++){
         if(name.equalsIgnoreCase(places.get(i).getName())){
             return true;
         }
     }
     return false;
    }


}


