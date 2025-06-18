package com.planatory.planatory; // Use your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.planatory.planatory.PlantCareActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button plantCareBtn = findViewById(R.id.plantCareBtn);
        plantCareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, PlantCareActivity.class);
            startActivity(intent);
        });
    }
}

