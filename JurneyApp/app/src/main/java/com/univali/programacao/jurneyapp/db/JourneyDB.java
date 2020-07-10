package com.univali.programacao.jurneyapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.univali.programacao.jurneyapp.dbHelper.DbHelper;
import com.univali.programacao.jurneyapp.model.ListName;
import com.univali.programacao.jurneyapp.model.Place;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JourneyDB {

    private static DbHelper dbHelper;
    private static String table_list_name = "list_name";
    private static String table_place = "place";

    public static void insert(String name, List<Place> places, Context context) {
        dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        long id = dbHelper.getWritableDatabase().insert(table_list_name, null, cv);
        cv.clear();
        for (Place place : places) {
            cv.put("list_name_id", id);
            cv.put("name", place.getName());
            cv.put("description", place.getDescription());
            cv.put("imageUrl", place.getImageUrl());
            dbHelper.getWritableDatabase().insert(table_place, null, cv);
            cv.clear();
        }
    }

    public static List<ListName> getAll(Context context) {
        dbHelper = new DbHelper(context);
        List<ListName> listNames = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase()
                .rawQuery("SELECT * FROM " + table_list_name, null);
        while (cursor.moveToNext()) {
            listNames.add(new ListName(cursor.getInt(0), cursor.getString(1)));
        }
        return listNames;

    }

    public static List<Place> getAllByListNameId(int id, Context context) {
        dbHelper = new DbHelper(context);
        List<Place> places = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = dbHelper.getWritableDatabase()
                .rawQuery("SELECT name, description, imageUrl FROM " + table_place + " " +
                                "WHERE list_name_id='" + id + "'",
                        null);
        while (cursor.moveToNext()) {
            Log.i("URL", cursor.getString(2));
            try {
                URL imageURL = new URL(cursor.getString(2));
                HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
                connection.setRequestMethod("GET");
                places.add(new Place(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        BitmapFactory.decodeStream(connection.getInputStream())
                ));
            } catch (IOException e) {
                Log.i("Exception", e.getMessage());
                e.printStackTrace();
            }
        }
        return places;
    }

}
