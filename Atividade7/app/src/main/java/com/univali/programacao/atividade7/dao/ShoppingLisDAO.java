package com.univali.programacao.atividade7.dao;

import android.content.Context;
import android.database.Cursor;

import com.univali.programacao.atividade7.helper.DbHelper;
import com.univali.programacao.atividade7.model.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingLisDAO {

    private static DbHelper dbHelper;

    public static List<ShoppingList> getAll(Context context){
        dbHelper = new DbHelper(context);
        List<ShoppingList> shoppingLists = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase().rawQuery("SELECT * FROM items", null);
        while (cursor.moveToNext()){
            shoppingLists.add(new ShoppingList(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4) == 1));
        }
        return shoppingLists;
    }

    public static void purchase(Context context, Integer id, Boolean purchase){
        dbHelper = new DbHelper(context);
        int flag = purchase ? 1 : 0;
        dbHelper.getWritableDatabase().execSQL("UPDATE items SET purchased = "+flag+" WHERE id = "+ id);
    }

    public static void deleteOne(Context context, Integer id){
        dbHelper = new DbHelper(context);
        dbHelper.getWritableDatabase().execSQL("DELETE FROM items WHERE id = "+id);
    }

}
