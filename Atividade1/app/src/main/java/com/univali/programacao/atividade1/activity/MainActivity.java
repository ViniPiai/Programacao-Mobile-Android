package com.univali.programacao.atividade1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.univali.programacao.atividade1.R;
import com.univali.programacao.atividade1.fragments.NumberFiveFragment;
import com.univali.programacao.atividade1.fragments.NumberFourFragment;
import com.univali.programacao.atividade1.fragments.NumberOneFragment;
import com.univali.programacao.atividade1.fragments.NumberThreeFragment;
import com.univali.programacao.atividade1.fragments.NumberTwoFragment;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberOneFragment numberOneFragment = new NumberOneFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameId, numberOneFragment);
                transaction.commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberTwoFragment numberTwoFragment = new NumberTwoFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameId, numberTwoFragment);
                transaction.commit();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberThreeFragment numberThreeFragment = new NumberThreeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameId, numberThreeFragment);
                transaction.commit();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberFourFragment numberFourFragment = new NumberFourFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameId, numberFourFragment);
                transaction.commit();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberFiveFragment numberFiveFragment = new NumberFiveFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameId, numberFiveFragment);
                transaction.commit();
            }
        });
    }
}
