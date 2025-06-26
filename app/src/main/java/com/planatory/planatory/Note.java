package com.planatory.planatory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;




import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


public class Note extends AppCompatActivity {

    DatabaseHelper dbHelper; // 1. Declare

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        EditText noteInput = findViewById(R.id.noteInput);
        Button addNoteBtn = findViewById(R.id.addNoteBtn);

        // Set listener to save note to SQLite
        addNoteBtn.setOnClickListener(v -> {
            String noteText = noteInput.getText().toString().trim();

            Button backHomeBtn = findViewById(R.id.backToHomeBtn);

            backHomeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Note.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            });



            if (!noteText.isEmpty()) {
                // Insert to database
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COL_NOTE_TEXT, noteText);

                long result = db.insert(DatabaseHelper.TABLE_NOTES, null, values);

                if (result != -1) {
                    Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
                    noteInput.setText(""); // clear input
                } else {
                    Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
                }

                db.close(); // optional
            } else {
                Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
