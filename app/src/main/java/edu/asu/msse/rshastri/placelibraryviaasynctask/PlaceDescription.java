package edu.asu.msse.rshastri.placelibraryviaasynctask;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by richashastri29 on 3/24/2017.
 */

// Copyright 2017 Richa Shastri
//
//
//I give the full right to Dr lindquist and Arizona State University to build my project and evaluate it or the purpose of determining your grade and program assessment.
//
//Purpose: The class Description class
//
// Ser423 Mobile Applications
//see http://pooh.poly.asu.edu/Mobile
//@author Richa Shastri Richa.Shastri@asu.edu
//        Software Engineering, CIDSE, ASU Poly
//@version February 12, 2017

public class PlaceDescription implements Serializable {
    String addressTitle;
    String addressStreet;
    double elevation;
    double latitude;
    double longitude;
    String name;
    String image;
    String description;
    String category;


    public PlaceDescription(String addressTitle, String addressStreet, double elevation, double latitude, double longitude, String name, String image, String description, String category) {


        this.addressTitle = addressTitle;
        this.addressStreet = addressStreet;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public PlaceDescription(JSONObject jsonObj){
        try{
            System.out.println("constructor from json received: "+
                    jsonObj.toString());
            name = jsonObj.getString("name");
            addressTitle = jsonObj.getString("address-title");
            addressStreet = jsonObj.getString("address-street");
            elevation = jsonObj.getDouble("elevation");
            latitude = jsonObj.getDouble("latitude");
            longitude = jsonObj.getDouble("longitude");
            image = jsonObj.getString("image");
            description = jsonObj.getString("description");
            category = jsonObj.getString("category");


        }catch(Exception ex){
            System.out.println(this.getClass().getSimpleName()+
                    ": error converting from json string");
        }
    }

    public JSONObject toJson(){
        JSONObject jo = new JSONObject();
        try{
            jo.put("name",name);
            jo.put("address-title",addressTitle);
            jo.put("address-street",addressStreet);
            jo.put("category",category);
            jo.put("elevation",elevation);
            jo.put("longitude",longitude);
            jo.put("latitude",latitude);
            jo.put("image",image);
            jo.put("description",description);

        }catch (Exception ex){
            System.out.println(this.getClass().getSimpleName()+
                    ": error converting to json");
        }
        return jo;
    }

    public String toJsonString(){
        String ret = "";
        try{
            ret = this.toJson().toString();
        }catch (Exception ex){
            System.out.println(this.getClass().getSimpleName()+
                    ": error converting to json string");
        }
        return ret;
    }
    public PlaceDescription(String jsonStr) {
        try {
            JSONObject jo = new JSONObject(jsonStr);
            addressTitle = jo.getString("address-title");
            addressStreet = jo.getString("address-street");
            elevation = jo.getDouble("elevation");
            latitude = jo.getDouble("latitude");
            longitude = jo.getDouble("longitude");
            name = jo.getString("name");
            image = jo.getString("image");
            description = jo.getString("description");
            category = jo.getString("category");

        }catch (Exception ex){
            android.util.Log.d(this.getClass().getSimpleName(), "error getting Student from json string");
        }

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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


}

