package com.univali.programacao.atividade3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.univali.programacao.atividade3.R;

import org.w3c.dom.Text;

public class FactorialResultActivity extends AppCompatActivity {

    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial_result);
        resultado = findViewById(R.id.resultadoFatorial);
        String result = getIntent().getStringExtra("fatorial");
        resultado.setText("Resultado: "+result);
    }
}
