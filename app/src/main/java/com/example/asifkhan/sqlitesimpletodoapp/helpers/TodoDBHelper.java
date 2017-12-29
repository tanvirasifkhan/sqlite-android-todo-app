package com.example.asifkhan.sqlitesimpletodoapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asifkhan.sqlitesimpletodoapp.models.PendingTodoModel;

import java.util.ArrayList;

/**
 * Created by asifkhan on 12/30/17.
 */

public class TodoDBHelper {
    private Context context;
    private DatabaseHelper databaseHelper;

    public TodoDBHelper(Context context){
        this.context=context;
        databaseHelper=new DatabaseHelper(context);
    }

    //add new todos into the database
    public boolean addNewTodo(PendingTodoModel pendingTodoModel){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_TODO_TITLE,pendingTodoModel.getTodoTitle());
        contentValues.put(DatabaseHelper.COL_TODO_CONTENT,pendingTodoModel.getTodoContent());
        contentValues.put(DatabaseHelper.COL_TODO_TAG,pendingTodoModel.getTodoTag());
        contentValues.put(DatabaseHelper.COL_TODO_DATE,pendingTodoModel.getTodoDate());
        contentValues.put(DatabaseHelper.COL_TODO_TIME,pendingTodoModel.getTodoTime());
        contentValues.put(DatabaseHelper.COL_TODO_STATUS,DatabaseHelper.COL_DEFAULT_STATUS);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_TODO_NAME,null,contentValues);
        sqLiteDatabase.close();
        return true;
    }

    //count todos from the database
    public int countTodos(){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String count="SELECT " + DatabaseHelper.COL_TODO_ID + " FROM " + DatabaseHelper.TABLE_TODO_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(count,null);
        return cursor.getCount();
    }

    //fetch all the todos from the database
    public ArrayList<PendingTodoModel> fetchAllTodos(){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        ArrayList<PendingTodoModel> pendingTodoModels=new ArrayList<>();
        String query="SELECT * FROM " + DatabaseHelper.TABLE_TODO_NAME+" INNER JOIN " + DatabaseHelper.TABLE_TAG_NAME+" ON " + DatabaseHelper.TABLE_TODO_NAME+"."+DatabaseHelper.COL_TODO_TAG+"="+
                DatabaseHelper.TABLE_TAG_NAME+"."+DatabaseHelper.COL_TAG_ID + " ORDER BY " + DatabaseHelper.TABLE_TODO_NAME+"."+DatabaseHelper.COL_TODO_ID + " DESC";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            PendingTodoModel pendingTodoModel=new PendingTodoModel();
            pendingTodoModel.setTodoID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TODO_ID)));
            pendingTodoModel.setTodoTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_TITLE)));
            pendingTodoModel.setTodoContent(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_CONTENT)));
            pendingTodoModel.setTodoTag(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TAG_TITLE)));
            pendingTodoModel.setTodoDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_DATE)));
            pendingTodoModel.setTodoTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_TIME)));
            pendingTodoModels.add(pendingTodoModel);
        }
        cursor.close();
        sqLiteDatabase.close();
        return pendingTodoModels;
    }

    //update todos according to the todos id
    public boolean updateTodo(PendingTodoModel pendingTodoModel){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_TODO_TITLE,pendingTodoModel.getTodoTitle());
        contentValues.put(DatabaseHelper.COL_TODO_CONTENT,pendingTodoModel.getTodoContent());
        contentValues.put(DatabaseHelper.COL_TODO_TAG,pendingTodoModel.getTodoTag());
        contentValues.put(DatabaseHelper.COL_TODO_DATE,pendingTodoModel.getTodoDate());
        contentValues.put(DatabaseHelper.COL_TODO_TIME,pendingTodoModel.getTodoTime());
        contentValues.put(DatabaseHelper.COL_TODO_STATUS,DatabaseHelper.COL_DEFAULT_STATUS);
        sqLiteDatabase.update(DatabaseHelper.TABLE_TODO_NAME,contentValues,DatabaseHelper.COL_TODO_ID+"=?",new String[]{String.valueOf(pendingTodoModel.getTodoID())});
        sqLiteDatabase.close();
        return true;
    }

    //remove todos according to the todos id
    public boolean removeTodo(int todoID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TODO_NAME,DatabaseHelper.COL_TODO_ID+"=?",new String[]{String.valueOf(todoID)});
        sqLiteDatabase.close();
        return true;
    }

    //fetch todos title from the database according the todos id
    public String fetchTodoTitle(int todoID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String query="SELECT " + DatabaseHelper.COL_TODO_TITLE + " FROM " + DatabaseHelper.TABLE_TODO_NAME + " WHERE " + DatabaseHelper.COL_TODO_ID+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(todoID)});
        String title="";
        if(cursor.moveToFirst()){
            title=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_TITLE));
        }
        cursor.close();
        sqLiteDatabase.close();
        return title;
    }

    //fetch todos content from the database according the todos id
    public String fetchTodoContent(int todoID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String query="SELECT " + DatabaseHelper.COL_TODO_CONTENT + " FROM " + DatabaseHelper.TABLE_TODO_NAME + " WHERE " + DatabaseHelper.COL_TODO_ID+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(todoID)});
        String content="";
        if(cursor.moveToFirst()){
            content=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_CONTENT));
        }
        cursor.close();
        sqLiteDatabase.close();
        return content;
    }

    //fetch todos date from the database according the todos id
    public String fetchTodoDate(int todoID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String query="SELECT " + DatabaseHelper.COL_TODO_DATE + " FROM " + DatabaseHelper.TABLE_TODO_NAME + " WHERE " + DatabaseHelper.COL_TODO_ID+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(todoID)});
        String date="";
        if(cursor.moveToFirst()){
            date=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_DATE));
        }
        cursor.close();
        sqLiteDatabase.close();
        return date;
    }

    //fetch todos time from the database according the todos id
    public String fetchTodoTime(int todoID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String query="SELECT " + DatabaseHelper.COL_TODO_TIME + " FROM " + DatabaseHelper.TABLE_TODO_NAME + " WHERE " + DatabaseHelper.COL_TODO_ID+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(todoID)});
        String time="";
        if(cursor.moveToFirst()){
            time=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TODO_TIME));
        }
        cursor.close();
        sqLiteDatabase.close();
        return time;
    }
}
