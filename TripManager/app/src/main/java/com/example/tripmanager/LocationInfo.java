package com.example.tripmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class LocationInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        String name = (String)getIntent().getSerializableExtra("name");
        String imageUrl = (String)getIntent().getSerializableExtra("imageUrl");
        String description = (String)getIntent().getSerializableExtra("description");

        TextView nameView = findViewById(R.id.nameView);
        TextView descriptionView = findViewById(R.id.descriptionView);

        nameView.setText(name);
        nameView.setTypeface(null, Typeface.BOLD);
        nameView.setTextSize(20);
        descriptionView.setText(description);
        descriptionView.setTextSize(14);
    }
}