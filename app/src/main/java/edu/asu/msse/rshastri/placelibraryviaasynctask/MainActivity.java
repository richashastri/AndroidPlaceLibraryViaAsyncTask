package edu.asu.msse.rshastri.placelibraryviaasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public ArrayAdapter<String> adapter;
    private ArrayList<String> placeList;

    private int returning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.util.Log.d(this.getClass().getSimpleName(), "Oncreate is called");

        placeList = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeList);
        ListView listView = (ListView) findViewById(R.id.placelist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        try {
            MethodInfo mi = new MethodInfo(this , "http://10.0.2.2:8080/", "getNames",
                    new Object[]{});
            AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
        } catch (Exception ex) {
            android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                    ex.getMessage());
        }


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position >= 0 && position <= placeList.size()) {
            Intent displayStud = new Intent(this, Main2Activity.class);
            displayStud.putExtra("selected", placeList.get(position));
            android.util.Log.d(this.getClass().getSimpleName(), "selection made:: ");
            android.util.Log.d(this.getClass().getSimpleName(), String.valueOf(placeList.get(position)));
            startActivityForResult(displayStud,1);

        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        placeList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeList);
        ListView listView = (ListView) findViewById(R.id.placelist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        try {
            MethodInfo mi = new MethodInfo(this , "http://10.0.2.2:8080/", "getNames",
                    new Object[]{});
            AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
        } catch (Exception ex) {
            android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                    ex.getMessage());
        }


    }
}
