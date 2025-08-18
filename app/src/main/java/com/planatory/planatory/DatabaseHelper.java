package com.planatory.planatory;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    public static final String DATABASE_NAME = "PlanatoryDatabase.db";
    public static final int DATABASE_VERSION = 1;

    // Table - Notes
    public static final String NOTES_TABLE = "Notes";
    public static final String NOTE_ID = "note_id";
    public static final String NOTE_TEXT = "note_text";

    // Table - Plant Tasks
    public static final String TASKS_TABLE = "PlantTasks";
    public static final String TASK_ID = "task_id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_STATUS = "is_done"; // 0 = not done, 1 = done

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Notes table
        String createNotesTable = "CREATE TABLE " + NOTES_TABLE + " ("
                + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOTE_TEXT + " TEXT)";

        // Create Tasks table
        String createTasksTable = "CREATE TABLE " + TASKS_TABLE + " ("
                + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TASK_NAME + " TEXT, "
                + TASK_STATUS + " INTEGER DEFAULT 0)";

        db.execSQL(createNotesTable);
        db.execSQL(createTasksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables if upgrading
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TASKS_TABLE);
        onCreate(db);
    }

    // Notes Section

    // Fetch all notes
    public Cursor getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + NOTES_TABLE + " ORDER BY " + NOTE_ID + " DESC",
                null
        );
    }

    // Delete a note by ID
    public int deleteNoteById(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NOTES_TABLE, NOTE_ID + "=?", new String[]{String.valueOf(noteId)});
    }
}
