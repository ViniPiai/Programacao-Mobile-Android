package com.example.infoscidadessc;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class InfosCidadesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_cidades_activity);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Cidade cidade = (Cidade) getIntent().getSerializableExtra("Cidade");
        ((TextView) findViewById(R.id.textViewOutputCidade)).setText(cidade.getNome());
        ((TextView) findViewById(R.id.textViewOutputCidade2)).setText(cidade.getNumeroPopulacao());
        ((TextView) findViewById(R.id.textViewOutputCidade3)).setText(cidade.getDensidadeDemográfica());

    }



}
