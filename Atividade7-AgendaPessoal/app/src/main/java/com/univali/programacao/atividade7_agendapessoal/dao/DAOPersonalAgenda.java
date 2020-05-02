package com.univali.programacao.atividade7_agendapessoal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.univali.programacao.atividade7_agendapessoal.helper.DbHelper;
import com.univali.programacao.atividade7_agendapessoal.model.PersonalAgenda;

import java.util.ArrayList;
import java.util.List;

public class DAOPersonalAgenda {

    private static DbHelper dbHelper;
    private static String table = "personal_agenda";
    public static void insertOne(PersonalAgenda personalAgenda, Context context){
        dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("name", personalAgenda.getName());
        cv.put("description", personalAgenda.getDescription());
        cv.put("date", personalAgenda.getDate().toString());
        dbHelper.getWritableDatabase().insert(table, null, cv);
    }

    public static void deleteOne(Integer id, Context context){
        dbHelper = new DbHelper(context);
        dbHelper.getWritableDatabase().execSQL("DELETE FROM "+table+" WHERE id="+ id);
    }

    public static void deleteAll(Context context){
        dbHelper = new DbHelper(context);
        dbHelper.getWritableDatabase().delete(table,null, null);
    }

    public static List<PersonalAgenda> getAll(Context context){
        dbHelper = new DbHelper(context);
        List<PersonalAgenda> personalAgendaList = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase()
                .rawQuery("SELECT * FROM "+table, null);
        while (cursor.moveToNext()){
            personalAgendaList.add(new PersonalAgenda(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3)));
        }
        return personalAgendaList;
    }


}
