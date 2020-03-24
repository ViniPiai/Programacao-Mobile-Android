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

public class NumberTwoFragment extends Fragment {

    private EditText coeficienteA, coeficienteB, coeficienteC;
    private TextView resultado;
    private Button btnCalcularRaizes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_two_fragment, container, false);
        coeficienteA = view.findViewById(R.id.coeA);
        coeficienteB = view.findViewById(R.id.coeB);
        coeficienteC = view.findViewById(R.id.coeC);
        resultado = view.findViewById(R.id.resultadoRaizes);
        btnCalcularRaizes = view.findViewById(R.id.btnCalcularRaizes);
        btnCalcularRaizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = convert(coeficienteA), b = convert(coeficienteB),
                        c = convert(coeficienteC);
                double delta = Math.sqrt(b-4*a*c);
                if(delta < 0){
                    resultado.setText("O delta é negativo");
                }else{
                    double r1 = (-b + delta)/2*a;
                    double r2 = (-b - delta)/2*a;
                    resultado.setText("R1: "+r1+", R2: "+r2);
                }
            }
        });
        return view;
    }

    private double convert(EditText e){
        return Double.parseDouble(e.getText().toString());
    }
}
