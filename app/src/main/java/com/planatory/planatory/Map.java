package com.planatory.planatory;

import android.content.Intent;                       // ← NEW
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
    private EditText fromLocation, toLocation;
    private Button directionBtn;
    private Button backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        fromLocation   = findViewById(R.id.fromLocation);
        toLocation     = findViewById(R.id.toLocation);
        directionBtn   = findViewById(R.id.directionBtn);
        backToHomeBtn  = findViewById(R.id.backToHomeBtn);


        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        directionBtn.setOnClickListener(v -> {
            String from = fromLocation.getText().toString().trim();
            String to   = toLocation.getText().toString().trim();
            if (!from.isEmpty() && !to.isEmpty()) {
                Toast.makeText(this,
                        "Showing directions from " + from + " to " + to,
                        Toast.LENGTH_SHORT).show();
                // TODO: call Directions API and draw polyline
            } else {
                Toast.makeText(this, "Enter both locations", Toast.LENGTH_SHORT).show();
            }
        });


        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, Home.class));  // Home = your main screen
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
