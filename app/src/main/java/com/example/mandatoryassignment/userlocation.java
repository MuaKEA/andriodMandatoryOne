package com.example.mandatoryassignment;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class userlocation extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    Button search;
    EditText city;
    private GoogleMap mMap;
    String Tag="userlocation class-->";
    double Longitude;
    double Latitude;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         setup();
       search.setOnClickListener(this);


    }

    private void setup() {
    city=findViewById(R.id.city);
    search=findViewById(R.id.search_button);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap; //læs documentation
        mMap.clear(); //fjerner alle de existerende pins som findes på map, logic ellers vil alle pinsenede blive siddende
        LatLng location = new LatLng(Latitude, Longitude); //her definere vi længde og bredegrad
        mMap.addMarker(new MarkerOptions().position(location).title(name)); // sætter en pin på location^
        mMap.animateCamera(CameraUpdateFactory.newLatLng(location),5000,null); // her rykker kortet til selve location i center
    }


    @Override
    public void onClick(View v) {
        if(Geocoder.isPresent()){
            try {
                String location = city.getText().toString(); // henter edittexten
                Log.d(Tag,city.getText().toString() +"<--");

                Geocoder gc = new Geocoder(this); //A class for handling geocoding and reverse geocoding
                List<Address> addresses= gc.getFromLocationName(location, 5); // get the found Address Objects

                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for(Address a : addresses){
                    if(a.hasLatitude() && a.hasLongitude()){ //hvis responsen har Latitude og Logubtude
                        Longitude= a.getLongitude(); // gemmer bredegrad
                        Latitude= a.getLatitude(); // gemmer længdegrad
                        name=a.getAddressLine(0); //metoden getAddressLine gemmer adresselinjen fra 0
                        Log.d(Tag,name +"<--");

                    }
                }
            } catch (IOException e) {
                // handle the exception
            }
        }
         // tager sig af map life cycle(autogenret af andrio studio)
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //håndtere ændringer i kortet osv. (autogeneret)
        mapFragment.getMapAsync(this);
    }

}
