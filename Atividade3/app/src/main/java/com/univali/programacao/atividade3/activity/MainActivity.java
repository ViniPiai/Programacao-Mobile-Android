package com.univali.programacao.atividade3.activity;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.univali.programacao.atividade3.R;
import com.univali.programacao.atividade3.adapter.AbasAdapter;
import com.univali.programacao.atividade3.fragment.Number1Fragment;
import com.univali.programacao.atividade3.fragment.Number2Fragment;
import com.univali.programacao.atividade3.fragment.Number3Fragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        AbasAdapter adapter = new AbasAdapter( getSupportFragmentManager() );
        adapter.adicionar( new Number1Fragment(), "1");
        adapter.adicionar(new Number2Fragment(), "2");
        adapter.adicionar( new Number3Fragment(), "3");


        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);



        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.bringToFront();

    }
}
