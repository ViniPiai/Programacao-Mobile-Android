package com.example.tripmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        PersonalTripList personalTripList = new PersonalTripList();

        ArrayList<Location> locations = personalTripList.searchAll(this);

        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(getBaseContext(), android.R.layout.simple_list_item_1, locations);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Location loc = (Location) adapterView.getItemAtPosition(i);
                String name = loc.getNome();
                String imageUrl = loc.getImageUrl();
                String description = loc.getDescricao();
                Intent intent = new Intent(getApplicationContext(), LocationInfo.class);
                intent.putExtra("name", name);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("description", description);
                startActivity(intent);
            }
        });

        FloatingActionButton bt = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), com.example.tripmanager.MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}