package com.planatory.planatory;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


public class Note extends AppCompatActivity {

    EditText noteInput;
    Button addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteInput = findViewById(R.id.noteInput);
        addNoteBtn = findViewById(R.id.addNoteBtn);

        addNoteBtn.setOnClickListener(v -> {
            String noteText = noteInput.getText().toString().trim();
            if (noteText.isEmpty()) {
                Toast.makeText(this, "Please write something", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
                noteInput.setText("");  // clear after saving
                // Future: Save to list or database
            }
        });

        Button backToHomeBtn = findViewById(R.id.backToHomeBtn);

        backToHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(Note.this, Home.class));
            finish(); // optional: close NotesActivity
        });

    }
}
