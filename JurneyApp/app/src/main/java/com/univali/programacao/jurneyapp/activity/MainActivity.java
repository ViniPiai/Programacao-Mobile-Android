package com.univali.programacao.jurneyapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.univali.programacao.jurneyapp.R;
import com.univali.programacao.jurneyapp.db.JourneyDB;
import com.univali.programacao.jurneyapp.model.ListName;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ListName> listNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listNames = JourneyDB.getAll(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("Minhas listas");
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewJorneyListActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<ListName> listNameArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNames);
        listView.setAdapter(listNameArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<ListName> listNames = JourneyDB.getAll(getApplicationContext());
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<ListName> listNameArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNames);
        listView.setAdapter(listNameArrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final List<ListName> listNames = JourneyDB.getAll(getApplicationContext());
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<ListName> listNameArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNames);
        listView.setAdapter(listNameArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), JourneyListActivity.class);
                intent.putExtra("id", listNames.get(i).getId());
                intent.putExtra("name", listNames.get(i).getName());
                startActivity(intent);
            }
        });
    }
}
