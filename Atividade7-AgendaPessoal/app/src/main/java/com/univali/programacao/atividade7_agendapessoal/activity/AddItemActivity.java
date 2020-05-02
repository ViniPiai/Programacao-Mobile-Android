package com.univali.programacao.atividade7_agendapessoal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.univali.programacao.atividade7_agendapessoal.R;
import com.univali.programacao.atividade7_agendapessoal.dao.DAOPersonalAgenda;
import com.univali.programacao.atividade7_agendapessoal.model.PersonalAgenda;
import com.univali.programacao.atividade7_agendapessoal.util.MaskEditUtil;

public class AddItemActivity extends AppCompatActivity {

    private EditText etName, etDescription, etDate, etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Adicionar evento");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etDate.addTextChangedListener(MaskEditUtil.mask(etDate, MaskEditUtil.FORMAT_DATE));
        etTime = findViewById(R.id.etTime);
        etTime.addTextChangedListener(MaskEditUtil.mask(etTime, MaskEditUtil.FORMAT_HOUR));
        Button btnAdicionar = findViewById(R.id.btnAdd);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                String date = etDate.getText().toString();
                String time = etTime.getText().toString();
                DAOPersonalAgenda.insertOne(
                        new PersonalAgenda(0, name, description, date + "\t"+time),
                        getApplicationContext());
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
