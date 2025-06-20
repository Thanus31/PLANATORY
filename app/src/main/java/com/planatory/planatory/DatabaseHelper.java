package com.planatory.planatory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "planatory.db";
    public static final int DB_VERSION = 1;

    // Table for Notes
    public static final String TABLE_NOTES = "notes";
    public static final String COL_NOTE_ID = "id";
    public static final String COL_NOTE_TEXT = "text";

    // Table for Plant Tasks
    public static final String TABLE_TASKS = "tasks";
    public static final String COL_TASK_ID = "id";
    public static final String COL_TASK_NAME = "name";
    public static final String COL_TASK_DONE = "done"; // 0 = not done, 1 = done

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNotes = "CREATE TABLE " + TABLE_NOTES + " (" +
                COL_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOTE_TEXT + " TEXT)";

        String createTasks = "CREATE TABLE " + TABLE_TASKS + " (" +
                COL_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TASK_NAME + " TEXT, " +
                COL_TASK_DONE + " INTEGER DEFAULT 0)";

        db.execSQL(createNotes);
        db.execSQL(createTasks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }
}

