package com.planatory.planatory;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText destinationInput;
    private Button searchBtn, backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        destinationInput = findViewById(R.id.toLocation);
        searchBtn = findViewById(R.id.directionBtn);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        searchBtn.setOnClickListener(v -> {
            String destination = destinationInput.getText().toString().trim();

            if (!destination.isEmpty()) {
                Geocoder geocoder = new Geocoder(Map.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocationName(destination, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(destination));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

                        Toast.makeText(this, "Showing: " + destination, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error finding location", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter destination", Toast.LENGTH_SHORT).show();
            }
        });

        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, Home.class));
            finish();
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Default view: Sri Lanka
        LatLng sriLanka = new LatLng(7.8731, 80.7718);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sriLanka, 6));
    }
}
