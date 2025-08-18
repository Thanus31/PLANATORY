package com.planatory.planatory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AddReminderActivity extends AppCompatActivity {

    private EditText taskInput;
    private Button addTaskBtn, backToHomeBtn;
    private RecyclerView reminderList;
    private ReminderAdapter adapter;
    private ArrayList<String> reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        taskInput = findViewById(R.id.taskInput);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);
        reminderList = findViewById(R.id.reminderList);

        // Initialize reminder list
        reminders = new ArrayList<>();
        adapter = new ReminderAdapter(this, reminders);
        reminderList.setLayoutManager(new LinearLayoutManager(this));
        reminderList.setAdapter(adapter);

        // Add task
        addTaskBtn.setOnClickListener(v -> {
            String newTask = taskInput.getText().toString().trim();
            if (newTask.isEmpty()) {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            } else {
                reminders.add(newTask);
                adapter.notifyItemInserted(reminders.size() - 1);
                taskInput.setText(""); // clear input
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
