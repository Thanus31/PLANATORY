package com.planatory.planatory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddReminderActivity extends AppCompatActivity {

    private EditText taskInput;
    private Button addTaskBtn, backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        // Initialize UI elements
        taskInput = findViewById(R.id.taskInput);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);

        // Add Task - send back to PlantCareActivity
        addTaskBtn.setOnClickListener(v -> {
            String newTask = taskInput.getText().toString().trim();

            if (newTask.isEmpty()) {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(AddReminderActivity.this, PlantCareActivity.class);
                intent.putExtra("new_task", newTask); // Pass new task back
                startActivity(intent);
                finish();
            }
        });

        // Back to Home
        backToHomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AddReminderActivity.this, Home.class);
            startActivity(intent);
            finish();
        });
    }
}
