package com.univali.programacao.atividade7.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DATABASE_NAME= "DB_SHOPPING_LIST";

    public DbHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "product VARCHAR, " +
                "type VARCHAR, " +
                "quantity VARCHAR, " +
                "purchased INTEGER DEFAULT 0" +
        ");";
        try {
            db.execSQL(sql);
            Log.i("INFO DB", "CRIADO");
        }catch (Exception e){
            Log.i("INFO DB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
