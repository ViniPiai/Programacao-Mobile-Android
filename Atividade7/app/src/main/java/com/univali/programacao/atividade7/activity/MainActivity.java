package com.univali.programacao.atividade7.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.univali.programacao.atividade7.AddItemActivity;
import com.univali.programacao.atividade7.R;
import com.univali.programacao.atividade7.adapter.AdapterShoppingList;
import com.univali.programacao.atividade7.dao.ShoppingLisDAO;
import com.univali.programacao.atividade7.helper.DbHelper;
import com.univali.programacao.atividade7.model.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ShoppingList> shoppingListList;
    private AdapterShoppingList adapterShoppingList;
    private RecyclerView recyclerView;
    private ShoppingList sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Carrinho de compras");
        setSupportActionBar(toolbar);
        createRecyclerView();


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        createRecyclerView();
        super.onStart();
    }

    private void createRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);

        shoppingListList = ShoppingLisDAO.getAll(getApplicationContext());
        adapterShoppingList = new AdapterShoppingList(shoppingListList, getApplicationContext());
        swipe();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterShoppingList.notifyDataSetChanged();
        recyclerView.setAdapter(adapterShoppingList);

    }

    private void swipe(){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                excluirMovimentacao(viewHolder);
            }
        };

        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
    }

    private void excluirMovimentacao(final RecyclerView.ViewHolder viewHolder){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Excluir item");
        alertDialog.setMessage("Deseja excluir o item da lista?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int position = viewHolder.getAdapterPosition();
                sl = shoppingListList.get(position);
                ShoppingLisDAO.deleteOne(getApplicationContext(), sl.getId());
                adapterShoppingList.notifyItemRemoved(position);
                adapterShoppingList.notifyDataSetChanged();
                createRecyclerView();
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                adapterShoppingList.notifyDataSetChanged();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
