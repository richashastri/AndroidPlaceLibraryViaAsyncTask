//Copyright 2017 Richa Shastri
//
//
//I give the full right to Dr lindquist and Arizona State University to build my project and evaluate it or the purpose of determining your grade and program assessment.
//
//Purpose: The second activity. It displays the placedescription of a place. You can add delete a place here.
//
// Ser423 Mobile Applications
//see http://pooh.poly.asu.edu/Mobile
//@author Richa Shastri Richa.Shastri@asu.edu
//        Software Engineering, CIDSE, ASU Poly
//@version March 24, 2017
package edu.asu.msse.rshastri.placelibraryviaasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AsynResponse {

    public Spinner courseSpinner;
    public static String j;
    public static String selecteddest;
    public EditText text1,text2,text3,text4,text5,text6,text7,text8,text9;
    public TextView textout2;
    public ArrayAdapter<String> aaA;
    public static PlaceDescription firstselection;

    public static PlaceDescription destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text1 = (EditText) findViewById(R.id.textid1);
        text2 = (EditText) findViewById(R.id.textid2);
        text3 = (EditText) findViewById(R.id.textid3);
        text4 = (EditText) findViewById(R.id.textid4);
        text5 = (EditText) findViewById(R.id.textid5);
        text6 = (EditText) findViewById(R.id.textid6);
        text7 = (EditText) findViewById(R.id.textid7);
        text8 = (EditText) findViewById(R.id.textid8);
        text9 = (EditText) findViewById(R.id.textid9);
        textout2 = (TextView) findViewById(R.id.distid);
        courseSpinner = (Spinner)findViewById(R.id.spindid);




        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            j = (String) b.get("selected");
            try{
                MethodInfo mi = new MethodInfo(this, "http://10.0.2.2:8080/","get",
                        new String[]{j});
                AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
                ac.delegate = this;
            } catch (Exception ex){
                android.util.Log.w(this.getClass().getSimpleName(),"Exception processing spinner selection: "+
                        ex.getMessage());
            }
        }




        //android.util.Log.d(this.getClass().getSimpleName(), String.valueOf(placedec.addressTitle));
        ArrayList<String> pl = new ArrayList<>();
        aaA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pl);
        courseSpinner.setAdapter(aaA);
        try {
            MethodInfo mi = new MethodInfo(this , "http://10.0.2.2:8080/", "getNames",
                    new Object[]{});
            AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
        } catch (Exception ex) {
            android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                    ex.getMessage());
        }
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                selecteddest = courseSpinner.getSelectedItem().toString();
                System.out.println("slecetion of spinner " + selecteddest);
                distancecal(selecteddest);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main2_menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        switch (item.getItemId()) {
            case R.id.add:

                text1 = (EditText) findViewById(R.id.textid1);
                text2 = (EditText) findViewById(R.id.textid2);
                text3 = (EditText) findViewById(R.id.textid3);
                text4 = (EditText) findViewById(R.id.textid4);
                text5 = (EditText) findViewById(R.id.textid5);
                text6 = (EditText) findViewById(R.id.textid6);
                text7 = (EditText) findViewById(R.id.textid7);
                text8 = (EditText) findViewById(R.id.textid8);
                text9 = (EditText) findViewById(R.id.textid9);

                String name = text1.getText().toString();
                String description  = text2.getText().toString();
                String category  = text3.getText().toString();
                String addresstitle  = text4.getText().toString();
                String addressstreet  = text5.getText().toString();
                String elevation = text6.getText().toString();
                String latitude = text7.getText().toString();
                String longitude = text8.getText().toString();
                String image = text9.getText().toString();

                PlaceDescription newplaceadded = new PlaceDescription(addresstitle, addressstreet, Double.parseDouble(elevation) , Double.parseDouble(latitude), Double.parseDouble(longitude), name, image, description, category);

                android.util.Log.d(this.getClass().getSimpleName(), newplaceadded.getName());
                android.util.Log.d(this.getClass().getSimpleName(), String.valueOf(newplaceadded));
                try{
                    MethodInfo mi = new MethodInfo(this, "http://10.0.2.2:8080/","add",
                            new Object[]{newplaceadded.toJson()});
                    AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
                } catch (Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                            ex.getMessage());
                }

                finish();
                return true;

            case R.id.remove:
                android.util.Log.d(this.getClass().getSimpleName(), "called remove");
                try{
                    MethodInfo mi = new MethodInfo(this, "http://10.0.2.2:8080/","remove",
                            new Object[]{j});
                    AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
                } catch (Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                            ex.getMessage());
                }

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void distancecal(String selecteddest) {
        System.out.println("Distance calculation");

        try{

            MethodInfo mi2 = new MethodInfo(this, "http://10.0.2.2:8080/","get",
                    new String[]{selecteddest});
            AsynConnectionCollect ac2 = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi2);
            ac2.delegate = this;
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception processing spinner selection: "+
                    ex.getMessage());


        }


    }

    public void goback(View view) {

        finish();

    }
    @Override
    public void processFinish(PlaceDescription output) {
        android.util.Log.w(this.getClass().getSimpleName(),"Interface called");

        text1.setText(Main2Activity.firstselection.name);
        text2.setText(Main2Activity.firstselection.description);
        text3.setText(Main2Activity.firstselection.category);
        text4.setText(Main2Activity.firstselection.addressTitle);
        text5.setText(Main2Activity.firstselection.addressStreet);
        text6.setText(" " + Main2Activity.firstselection.elevation);
        text7.setText(" " + Main2Activity.firstselection.latitude);
        text8.setText(" " + Main2Activity.firstselection.longitude);
        text9.setText(Main2Activity.firstselection.image);

        Double lat1 = firstselection.getLatitude();
        Double long1 = firstselection.getLongitude();
        Double lat2 = destination.getLatitude();
        Double long2 = destination.getLongitude();

        Double R = 6371e3; // metres

        Double φ1 = Math.toRadians(lat1);
        Double φ2 = Math.toRadians(lat2);
        Double Δφ = Math.toRadians(lat2-lat1);
        Double Δλ = Math.toRadians(long2-long1);

        Double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Double d = R * c;
        textout2.setText(d.toString());


    }

}

