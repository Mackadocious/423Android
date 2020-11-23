

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
 * @Description: PlaceDescription creates individual place objects and stores their attributes.
 *
 */


package logic;

import android.content.Context;

public class PlaceDescription {


    String name;
    String description;
    String category;
    String addressTitle;
    String addressStreet;
    double elevation;
    double latitude;
    double longtitude;
    Context context;
    String path;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }



}
