package com.univali.programacao.atividade3.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.univali.programacao.atividade3.R;

@SuppressLint("Registered")
public class Atividade2Log extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.atividade_2);
        Log.d("Create screen", "Create");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Resume screen", "Resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Start screen", "Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Stop screen", "Stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Pause screen", "Pausou");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Restart screen", "Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Destroy screen", "Destroy");
    }
}
