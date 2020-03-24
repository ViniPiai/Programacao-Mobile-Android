package com.univali.programacao.atividade1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.univali.programacao.atividade1.R;

public class NumberOneFragment extends Fragment {

    private EditText quantAulas, quantFaltas;
    private TextView resultado;
    private Button btnCalcular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_one_fragment, container, false);
        quantAulas = view.findViewById(R.id.quantAulas);
        quantFaltas = view.findViewById(R.id.quantFrequencia);
        resultado = view.findViewById(R.id.resultadoFrequencia);
        btnCalcular = view.findViewById(R.id.btnCalcularFrequencia);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double aulas = Double.parseDouble(quantAulas.getText().toString());
                double faltas = Double.parseDouble(quantFaltas.getText().toString());
                double frequencia = (faltas*100)/aulas;
                resultado.setText("Frequencia: " + frequencia);
            }
        });
        return view;
    }
}
