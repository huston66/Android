package com.example.zhenli.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.hamweather.aeris.communication.AerisEngine;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.maps.AerisMapView.AerisMapType;
import com.hamweather.aeris.maps.MapViewFragment;
import com.hamweather.aeris.maps.interfaces.OnAerisMapLongClickListener;
import com.hamweather.aeris.tiles.AerisTile;

/**
 * Created by zhenli on 11/22/15.
 */
public class MapFragment extends MapViewFragment implements OnAerisMapLongClickListener
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AerisEngine.initWithKeys(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret), "com.example.zhenli.myapplication");
        View view = inflater.inflate(R.layout.fragment_interactive_maps, container, false);
        mapView = (AerisMapView)view.findViewById(R.id.aerisfragment_map);
        mapView.init(savedInstanceState, AerisMapType.GOOGLE);

        mapView.moveToLocation(new LatLng(Main22Activity.lat, Main22Activity.lon), 14);
        mapView.addLayer(AerisTile.RADSAT);
        return view;
//        View view = inflater.inflate(R.layout.fragment_interactive_maps, container, false);
//        mapView = (AerisMapView)view.findViewById(R.id.aerisfragment_map);
//        mapView.init(savedInstanceState, AerisMapType.GOOGLE);
//        mapView.setOnAerisMapLongClickListener(this);
//
//        return view;
    }

    @Override
    public void onMapLongClick(double lat, double longitude) {
        // code to handle map long press. i.e. Fetch current conditions?
        // see demo app MapFragment.java
    }
}
