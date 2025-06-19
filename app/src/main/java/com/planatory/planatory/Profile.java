package com.planatory.planatory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    Button editProfileBtn, backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfileBtn = findViewById(R.id.editProfileBtn);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);

        editProfileBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Edit Profile feature coming soon", Toast.LENGTH_SHORT).show();
        });

        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(Profile.this, Home.class));
            finish();
        });
    }
}
