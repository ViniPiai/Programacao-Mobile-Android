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

public class NumberThreeFragment extends Fragment {

    private EditText catetoB, catetoC;
    private TextView resultado;
    private Button btnCalcularHipo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_three_fragment, container, false);
        catetoB = view.findViewById(R.id.catB);
        catetoC = view.findViewById(R.id.catC);
        resultado = view.findViewById(R.id.resultadoHipo);
        btnCalcularHipo = view.findViewById(R.id.btnCalcularHipo);
        btnCalcularHipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double b = convert(catetoB), c = convert(catetoC);
                double hipo = Math.sqrt(b*b + c*c);
                resultado.setText("Hipotenusa: "+hipo);
            }
        });
        return view;
    }

    private Double convert(EditText e){
        return Double.parseDouble(e.getText().toString());
    }
}
