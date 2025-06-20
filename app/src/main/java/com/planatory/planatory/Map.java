package com.planatory.planatory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText fromLocation, toLocation;
    Button directionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        directionBtn = findViewById(R.id.directionBtn);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        directionBtn.setOnClickListener(v -> {
            String from = fromLocation.getText().toString();
            String to = toLocation.getText().toString();
            if (!from.isEmpty() && !to.isEmpty()) {
                // In real app, use Geocoding & Directions API
                Toast.makeText(this, "Showing directions from " + from + " to " + to, Toast.LENGTH_SHORT).show();
                // You can call Google Directions API and parse response to draw Polyline here
            } else {
                Toast.makeText(this, "Enter both locations", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Show Sri Lanka as default location
        LatLng sriLanka = new LatLng(7.8731, 80.7718);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sriLanka, 6));
    }
}