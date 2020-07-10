package com.univali.programacao.jurneyapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import com.univali.programacao.jurneyapp.R;
import com.univali.programacao.jurneyapp.adapter.PlaceAdapter;
import com.univali.programacao.jurneyapp.adapter.PlaceAdapter2;
import com.univali.programacao.jurneyapp.db.JourneyDB;
import com.univali.programacao.jurneyapp.model.Place;

import java.io.IOException;
import java.util.List;

public class JourneyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_list);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle(getIntent().getStringExtra("name"));
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        List<Place> places = JourneyDB.getAllByListNameId(getIntent().getIntExtra("id",
                0), getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.rvPlaces2);
        PlaceAdapter2 placeAdapter2 = new PlaceAdapter2(places);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(placeAdapter2);

    }
}
