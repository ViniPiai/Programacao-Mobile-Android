package com.example.tripmanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalTripList {

    public static void insertNewLocation(Activity activity, Location location){
        SQLiteDatabase db = DAOSQLiteTripManager.getDBInstance(activity);

        String[] arrayParam= new String[3];
        arrayParam[0] = location.getNome();
        arrayParam[1] = location.getImageUrl();
        arrayParam[2] = location.getDescricao();
        db.execSQL("insert into item (nome, imageUrl, description) values(?,?,?)", arrayParam);
    }

//    public static void deleta(Activity activity, String nome){
//        SQLiteDatabase db = DAOSQLiteAgendaEvento.getDBInstance(activity);
//        db.execSQL("delete from item where nome='" + nome + "'");
//    }

    public static void clearList(Activity activity){
        SQLiteDatabase db = DAOSQLiteTripManager.getDBInstance(activity);
        db.execSQL("delete from item");
    }

    public static ArrayList<Location> searchAll(Activity activity){
        ArrayList<Location> lista= new ArrayList<Location>();
        Cursor cursor= DAOSQLiteTripManager.getDBInstance(activity).rawQuery("select nome, imageUrl, description from item",null);
        while(cursor.moveToNext()) {
            String nome = cursor.getString(0);
            String imageUrl = cursor.getString(1);
            String descricao = cursor.getString(2);
            Location item = new Location(nome, imageUrl, descricao);
            lista.add(item);
        }
        return lista;
    }

//    public static List<ItemAgendaEvento> buscaTextual(Activity activity, String filtro){
//        ArrayList<ItemAgendaEvento> lista= new ArrayList<ItemAgendaEvento>();
//        Cursor cursor= DAOSQLiteAgendaEvento.getDBInstance(activity).rawQuery("select nome, descricao, date from item order by date",null);
//        while(cursor.moveToNext()) {
//            ItemAgendaEvento item= new ItemAgendaEvento();
//            String nome= cursor.getString(0);
//            String descricao=cursor.getString(1);
//            String dateTxt=cursor.getString(2);
//            if (nome.contains(filtro) || descricao.contains(filtro)) {
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                Date data = null;
//                try {
//                    data = format.parse(dateTxt);
//                } catch (Exception e) {
//                    data = new Date();
//                }
//
//                item.setNome(nome);
//                item.setDescricao(descricao);
//                item.setData(data);
//                lista.add(item);
//                Log.i("SQLLite", nome);
//            }
//        }
//        return lista;
//    }

}
