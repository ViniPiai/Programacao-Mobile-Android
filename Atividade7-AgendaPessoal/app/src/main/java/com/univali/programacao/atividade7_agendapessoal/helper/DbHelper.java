package com.univali.programacao.atividade7_agendapessoal.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DATABASE_NAME= "DB_PERSONAL_AGENDA";

    public DbHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS personal_agenda (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, " +
                "description VARCHAR, " +
                "date VARCHAR );";
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
