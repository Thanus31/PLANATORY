package com.planatory.planatory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Note extends AppCompatActivity {

    DatabaseHelper dbHelper;
    RecyclerView notesRecyclerView;
    NoteAdapter noteAdapter;
    ArrayList<String> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        dbHelper = new DatabaseHelper(this);
        EditText noteInput = findViewById(R.id.noteInput);
        Button addNoteBtn = findViewById(R.id.addNoteBtn);
        Button backHomeBtn = findViewById(R.id.backToHomeBtn);

        // Setup RecyclerView
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(noteList);
        notesRecyclerView.setAdapter(noteAdapter);

        // Load saved notes initially
        loadNotes();

        addNoteBtn.setOnClickListener(v -> {
            String noteText = noteInput.getText().toString().trim();

            if (!noteText.isEmpty()) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.NOTE_TEXT, noteText); // updated constant

                long result = db.insert(DatabaseHelper.NOTES_TABLE, null, values); // updated constant

                if (result != -1) {
                    Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
                    noteInput.setText("");
                    loadNotes(); // refresh notes list
                } else {
                    Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
                }

                db.close();
            } else {
                Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show();
            }
        });

        backHomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Note.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void loadNotes() {
        noteList.clear();
        Cursor cursor = dbHelper.getAllNotes();
        if (cursor.moveToFirst()) {
            do {
                String note = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NOTE_TEXT)); // updated constant
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        noteAdapter.notifyDataSetChanged();
    }
}
