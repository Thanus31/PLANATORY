package com.planatory.planatory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPlantActivity extends AppCompatActivity {

    ImageView plantImage;
    Button uploadImageBtn, submitPlantBtn, backToHomeBtn;
    EditText plantNameInput, plantLocationInput, plantDescriptionInput;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        plantImage = findViewById(R.id.plantImage);
        uploadImageBtn = findViewById(R.id.uploadImageBtn);
        submitPlantBtn = findViewById(R.id.submitPlantBtn);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);

        plantNameInput = findViewById(R.id.plantNameInput);
        plantLocationInput = findViewById(R.id.plantLocationInput);
        plantDescriptionInput = findViewById(R.id.plantDescriptionInput);

        uploadImageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        submitPlantBtn.setOnClickListener(v -> {
            String name = plantNameInput.getText().toString().trim();
            String location = plantLocationInput.getText().toString().trim();
            String description = plantDescriptionInput.getText().toString().trim();

            if (name.isEmpty() || location.isEmpty() || description.isEmpty() || imageUri == null) {
                Toast.makeText(this, "Please complete all fields and upload an image", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Plant submitted!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(AddPlantActivity.this, SwapScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });


        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(AddPlantActivity.this, Home.class));
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            plantImage.setImageURI(imageUri);
        }
    }
}
