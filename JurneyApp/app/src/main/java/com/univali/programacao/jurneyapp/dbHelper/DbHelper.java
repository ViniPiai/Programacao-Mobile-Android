package com.univali.programacao.jurneyapp.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static int VERSION = 1;
    static String DATABASE_NAME = "DB_PERSONAL_AGENDA";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS list_name (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR)";
        String sql1 = "CREATE TABLE IF NOT EXISTS place (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "list_name_id INTEGER," +
                "name VARCHAR, " +
                "description VARCHAR," +
                "imageUrl TEXT)";
        try {
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.execSQL(sql1);
            Log.i("INFO DB", "CRIADO");
        }catch (Exception e){
            Log.i("INFO DB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
