package com.planatory.planatory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SwapScreenActivity extends AppCompatActivity {

    ImageView newPlantImage;
    TextView newPlantDetails, newPlantMobile;
    Button backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap_screen);

        // Reference to views
        newPlantImage = findViewById(R.id.newPlantImage);
        newPlantDetails = findViewById(R.id.newPlantDetails);
        newPlantMobile = findViewById(R.id.newPlantMobile);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);

        // Get data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("plantName");
        String location = intent.getStringExtra("plantLocation");
        String description = intent.getStringExtra("plantDescription");
        String imageUriString = intent.getStringExtra("plantImageUri");
        String mobile = intent.getStringExtra("plantMobile");

        // Display plant info if available
        if (name != null && location != null && description != null && imageUriString != null && mobile != null) {
            Uri imageUri = Uri.parse(imageUriString);
            newPlantImage.setImageURI(imageUri);
            newPlantDetails.setText(name + " - " + location + "\n" + description);
            newPlantMobile.setText("Contact: " + mobile);
        }

        // Back to home
        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(SwapScreenActivity.this, Home.class));
            finish();
        });
    }
}
