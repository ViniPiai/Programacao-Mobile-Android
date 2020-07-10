package com.example.tripmanager;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DAOSQLiteTripManager extends SQLiteOpenHelper {
    private static final String dbname= "trip_list";

    public DAOSQLiteTripManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table item (chave integer primary key autoincrement, nome varchar(50), imageUrl varchar(100), description varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static SQLiteDatabase getDBInstance(Activity activity){
        DAOSQLiteTripManager dao = new DAOSQLiteTripManager(activity.getBaseContext(), DAOSQLiteTripManager.dbname , null, 1);
        return dao.getWritableDatabase();
    }
}
