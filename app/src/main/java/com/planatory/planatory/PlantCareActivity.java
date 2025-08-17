package com.planatory.planatory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlantCareActivity extends AppCompatActivity {

    private Button addReminderBtn;
    private Button backToHomeBtn; // ← NEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care);

        // + Add reminder
        addReminderBtn = findViewById(R.id.addReminderBtn);
        addReminderBtn.setOnClickListener(v ->
                startActivity(new Intent(this, AddReminderActivity.class))
        );

        // Back to Home
        backToHomeBtn = findViewById(R.id.backToHomeBtn); // same ID as in XML
        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, Home.class)); // Home = your main screen
            finish(); // optional – closes this screen
        });
    }
}
