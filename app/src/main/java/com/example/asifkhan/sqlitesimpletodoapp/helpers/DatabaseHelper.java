package com.example.asifkhan.sqlitesimpletodoapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asifkhan on 12/29/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="todomanager";

    //tag table and columns
    public static final String TABLE_TAG_NAME="tags";
    public static final String COL_TAG_ID="tag_id";
    public static final String COL_TAG_TITLE="tag_title";

    //todos table and columns
    public static final String TABLE_TODO_NAME="todos";
    public static final String COL_TODO_ID="todo_id";
    public static final String COL_TODO_TITLE="todo_title";
    public static final String COL_TODO_CONTENT="todo_content";
    public static final String COL_TODO_TAG="todo_tag";
    public static final String COL_TODO_DATE="todo_date";
    public static final String COL_TODO_START_TIME="todo_start_time";
    public static final String COL_TODO_END_TIME="todo_end_time";

    //forcing foreign key
    public static final String FORCE_FOREIGN_KEY="PRAGMA foreign_keys=ON";

    //creating tags table query
    private static final String CREATE_TAGS_TABLE="CREATE TABLE IF NOT EXISTS " + TABLE_TAG_NAME+"("+
            COL_TAG_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            COL_TAG_TITLE+" TEXT NOT NULL UNIQUE"+")";

    //creating todos table query
    private static final String CREATE_TODOS_TABLE="CREATE TABLE IF NOT EXISTS " + TABLE_TODO_NAME+"("+
            COL_TODO_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            COL_TODO_TITLE+" TEXT NOT NULL UNIQUE"+COL_TODO_CONTENT+" TEXT NOT NULL,"+
            COL_TODO_TAG +" INTEGER NOT NULL,"+COL_TODO_DATE+" TEXT NOT NULL,"+
            COL_TODO_START_TIME+" TEXT NOT NULL,"+COL_TODO_END_TIME+" TEXT NOT NULL,"+
            "FOREIGN KEY("+COL_TODO_TAG+") REFERENCES "+CREATE_TAGS_TABLE+"("+COL_TAG_ID+") ON UPDATE CASCADE ON DELETE CASCADE"+")";

    //dropping tags table
    private static final String DROP_TAGS_TABLE="DROP TABLE IF EXISTS " + TABLE_TAG_NAME;
    //dropping todos table
    private static final String DROP_TODOS_TABLE="DROP TABLE IF EXISTS " + TABLE_TODO_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TAGS_TABLE);
        sqLiteDatabase.execSQL(CREATE_TODOS_TABLE);
        sqLiteDatabase.execSQL(FORCE_FOREIGN_KEY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TAGS_TABLE);
        sqLiteDatabase.execSQL(DROP_TODOS_TABLE);
        onCreate(sqLiteDatabase);
    }
}