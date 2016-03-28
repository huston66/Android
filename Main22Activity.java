package com.example.zhenli.myapplication;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Main22Activity extends AppCompatActivity {
    static double lat;
    static double lon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        try {
            if (findViewById(R.id.fragment_container) != null) {
                if (savedInstanceState != null) {
                    return;
                }

                JSONObject obj = new JSONObject(getIntent().getStringExtra("updateui"));
                lat = obj.getDouble("latitude");
                lon = obj.getDouble("longitude");

                MapFragment fragment = new MapFragment();
                FragmentTransaction ft= getFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, fragment).commit();
            }

//            MapFragment fragment = new MapFragment();
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            if(fragment != null)
//                ft.add(R.id.start_fragment, fragment);

        } catch (JSONException e) {

        }
    }
}


