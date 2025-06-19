package com.planatory.planatory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PlantCareActivity extends AppCompatActivity {

    Button addReminderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care);

        addReminderBtn = findViewById(R.id.addReminderBtn);
        addReminderBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PlantCareActivity.this, AddReminderActivity.class);
            startActivity(intent);
        });
    }
}


