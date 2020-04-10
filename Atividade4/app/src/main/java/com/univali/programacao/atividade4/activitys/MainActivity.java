package com.univali.programacao.atividade4.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.univali.programacao.atividade4.CityInfo;
import com.univali.programacao.atividade4.R;
import com.univali.programacao.atividade4.model.Cidade;
import com.univali.programacao.atividade4.model.Cidades;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText cidadeFilter;
    private ListView listView;
    private List<Cidade> citys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cidadeFilter = findViewById(R.id.etSearch);
        listView = findViewById(R.id.lvCitys);
        citys = Cidades.getCidades();
        final ArrayAdapter<Cidade> adapter =
                new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, citys);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cidade c = (Cidade) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), CityInfo.class);
                intent.putExtra("cidade", c);
                startActivity(intent);
            }
        });
        cidadeFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }
}
