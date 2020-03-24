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

public class NumberFourFragment extends Fragment {

    private EditText nota1, nota2, nota3, peso1, peso2, peso3;
    private TextView resultado;
    private Button btnCalcularMedia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_four_fragment, container, false);
        nota1 = view.findViewById(R.id.nota1);
        nota2 = view.findViewById(R.id.nota2);
        nota3 = view.findViewById(R.id.nota3);
        peso1 = view.findViewById(R.id.peso1);
        peso2 = view.findViewById(R.id.peso2);
        peso3 = view.findViewById(R.id.peso3);
        resultado = view.findViewById(R.id.resultadoMedia);
        btnCalcularMedia = view.findViewById(R.id.btnCalcularMedia);
        btnCalcularMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double n1 = con(nota1), n2 = con(nota2), n3 = con(nota3),
                        p1 = con(peso1), p2 = con(peso2), p3 = con(peso3);
                double media = (n1*p1 + n2*p2 + n3*p3)/(p1+p2+p3);
                resultado.setText("Média: "+media);
            }
        });
        return view;
    }

    private double con(EditText e){
        return Double.parseDouble(e.getText().toString());
    }
}
