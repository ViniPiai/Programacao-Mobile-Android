package com.univali.programacao.atividade2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.univali.programacao.atividade2.R;
import com.univali.programacao.atividade2.adapters.AbasAdapter;
import com.univali.programacao.atividade2.fragments.NumberOneFragment;
import com.univali.programacao.atividade2.fragments.NumberThreeFragment;
import com.univali.programacao.atividade2.fragments.NumberTwoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AbasAdapter adapter = new AbasAdapter( getSupportFragmentManager() );
        adapter.adicionar( new NumberOneFragment() , "Primeira Aba");
        adapter.adicionar( new NumberTwoFragment(), "Segunda Aba");
        adapter.adicionar( new NumberThreeFragment(), "Terceira Aba");

        ViewPager viewPager = findViewById(R.id.abas_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.abas);
        tabLayout.setupWithViewPager(viewPager);
    }
}
