package com.univali.programacao.atividade2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.univali.programacao.atividade2.R;
import com.univali.programacao.atividade2.fragments.NumberOneFragment;
import com.univali.programacao.atividade2.fragments.NumberThreeFragment;
import com.univali.programacao.atividade2.fragments.NumberTwoFragment;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(view -> {
            NumberOneFragment numberOneFragment = new NumberOneFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameId, numberOneFragment);
            transaction.commit();
        });
        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(view -> {
            NumberTwoFragment numberTwoFragment = new NumberTwoFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameId, numberTwoFragment);
            transaction.commit();
        });
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(view -> {
            NumberThreeFragment numberThreeFragment = new NumberThreeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameId, numberThreeFragment);
            transaction.commit();
        });
    }
}
