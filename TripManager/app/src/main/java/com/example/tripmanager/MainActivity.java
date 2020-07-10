package com.example.tripmanager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TripList tripList = null;
        try {
            tripList = new TripList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Location> locations = tripList.getLocations();

        final PersonalTripList personalTripList = new PersonalTripList();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        final ArrayList<Location> itensSelecionados = new ArrayList<Location>();

        for (Location location: locations){
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.HORIZONTAL);//LinearLayout.VERTICAL
            layout.setPadding(0,0,0,15);

            String place;
            String country;
            if (location.getNome().contains(" - ") || location.getNome().contains(" – ")){
                try {
                    String[] name = location.getNome().split(" - ");
                    place = name[0];
                    country = name[1];
                } catch (Exception e) {
                    String[] name = location.getNome().split(" – ");
                    place = name[0];
                    country = name[1];
                }
            } else {
                place = location.getNome();
                country = location.getNome();
            }

            LinearLayout lay = new LinearLayout(this);
            lay.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lay.setOrientation(LinearLayout.VERTICAL);//LinearLayout.VERTICAL
            lay.setPadding(0,0,0,100);

            TextView tv = new TextView(this);
            tv.setText(place);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextSize(20);
            tv.setWidth(350);

            LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(p1);
            lay.addView(tv);

            tv = new TextView(this);
            tv.setText(country);
            tv.setTextSize(18);
            lay.addView(tv);

            layout.addView(lay);

            CheckBox cb = new CheckBox(this);

            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            cb.setLayoutParams(p3);
            tv.setWidth(450);
            cb.setTag(location);
            cb.setGravity(Gravity.END);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Location loc =(Location) v.getTag();
                    CheckBox cb=(CheckBox)v;
                    if(cb.isChecked()){
                        itensSelecionados.add(loc);
                    }else{
                        itensSelecionados.remove(loc);
                    }
                }
            });

            layout.addView(cb);

            linearLayout.addView(layout);

        }

        Button button = (Button)findViewById(R.id.addButton);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Location loc : itensSelecionados) {
                    personalTripList.insertNewLocation(MainActivity.this, loc);
                }
                ArrayList<Location> loc = personalTripList.searchAll(MainActivity.this);
                for (Location location1 : loc) {
                    System.out.println(location1.getNome());
                }
//                personalTripList.clearList(MainActivity.this);
            }
        });

//        System.out.println("TESTE MUITO LOUCO");
//        String nome = "floripa";
//        String imageUrl = "url";
//        String description = "teste de execucao";
//        Location location = new Location(nome, imageUrl, description);
//        personalTripList.insertNewLocation(this, location);
//        ArrayList<Location> ll = personalTripList.searchAll(this);
//        for (Location location1: ll){
//            System.out.println(location1.getNome());
//        }
//        personalTripList.clearList(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mylist) {
            Intent myIntent = new Intent(getBaseContext(), com.example.tripmanager.MyList.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
