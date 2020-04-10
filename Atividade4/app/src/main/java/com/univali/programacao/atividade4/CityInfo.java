package com.univali.programacao.atividade4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.univali.programacao.atividade4.model.Cidade;

public class CityInfo extends AppCompatActivity {

    private TextView city, pop, area, denPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);
        city = findViewById(R.id.tvCityName);
        pop = findViewById(R.id.tvPopulation);
        area = findViewById(R.id.tvArea);
        denPop = findViewById(R.id.tvDenPop);
        Cidade cidade = (Cidade) getIntent().getSerializableExtra("cidade");
        city.setText(cidade.getNome());
        pop.setText(cidade.getPopulacao());
        area.setText(cidade.getArea());
        denPop.setText(cidade.getDensidadePopulacional());
    }
}
