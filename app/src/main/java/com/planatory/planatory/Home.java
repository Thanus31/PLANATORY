package com.planatory.planatory; // Use your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Plant Care Button
        Button plantCareBtn = findViewById(R.id.plantCareBtn);
        plantCareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, PlantCareActivity.class);
            startActivity(intent);
        });

        //Plant Swap Button
        Button plantSwapBtn = findViewById(R.id.plantSwapBtn);
        plantSwapBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, AddPlantActivity.class);
            startActivity(intent);
        });

        //Note Button
        Button notesBtn = findViewById(R.id.notesBtn);
        notesBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Note.class);
            startActivity(intent);
        });

        //Map Button
        Button mapBtn = findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Map.class);
            startActivity(intent);
        });

        //Profile Button
        Button profileBtn = findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Profile.class);
            startActivity(intent);
        });

        //Check Light Button
        Button checkLightBtn = findViewById(R.id.btnSensor);
        checkLightBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, LightSensorActivity.class);
            startActivity(intent);
        });
    }
}
