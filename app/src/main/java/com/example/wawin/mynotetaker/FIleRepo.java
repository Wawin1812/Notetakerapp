package com.example.wawin.mynotetaker;

/**
 * Created by Wawin on 3/29/16.
 */

import android.content.Context;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class FileRepo {
    private DBHelper dbHelper;

    public FileRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(File file) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(File.KEY_name, file.name);
        //long id = db.insert(File.CURRENT_TIMESTAMP, null, values);

        // Inserting Row
        long file_Id = db.insert(File.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) file_Id;
    }

    public File info(int file_Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                File.KEY_ID + "," +
                "CURRENT_TIMESTAMP" +
                " FROM " + File.TABLE
                + " WHERE " +
                File.KEY_ID + "=?";

        int iCount = 0;
        File file = new File();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(file_Id)});

        if (cursor.moveToFirst()) {
            do {
                file.file_ID = cursor.getInt(cursor.getColumnIndex(File.KEY_ID));
                file.time = cursor.getString(cursor.getColumnIndex("CURRENT_TIMESTAMP"));
                //file.time = cursor.getString(cursor.getColumnIndex(File.CURRENT_TIMESTAMP));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return file;
    }


    public void delete(int file_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(File.TABLE, File.KEY_ID + "= ?", new String[]{String.valueOf(file_Id)});
        db.close(); // Closing database connection
    }

    public void update(File file) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(File.KEY_name, file.name);

        db.update(File.TABLE, values, File.KEY_ID + "= ?", new String[]{String.valueOf(file.file_ID)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getFileList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = " SELECT " +
                File.KEY_ID + "," +
                File.KEY_name +
                //File.CURRENT_TIMESTAMP +
                " FROM " + File.TABLE;

        ArrayList<HashMap<String, String>> fileList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> file = new HashMap<>();
                file.put("id", cursor.getString(cursor.getColumnIndex(File.KEY_ID)));
                file.put("name", cursor.getString(cursor.getColumnIndex(File.KEY_name)));
                //file.put("time", cursor.getString(cursor.getColumnIndex(File.CURRENT_TIMESTAMP)));
                fileList.add(file);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fileList;

    }

    public File getFIleById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                File.KEY_ID + "," +
                File.KEY_name +
                //File.CURRENT_TIMESTAMP +
                " FROM " + File.TABLE
                + " WHERE " +
                File.KEY_ID + "=?";

        int iCount = 0;
        File file = new File();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                file.file_ID = cursor.getInt(cursor.getColumnIndex(File.KEY_ID));
                file.name = cursor.getString(cursor.getColumnIndex(File.KEY_name));
                //file.time = cursor.getString(cursor.getColumnIndex(File.CURRENT_TIMESTAMP));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return file;
    }

}