package com.univali.programacao.atividade2.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.univali.programacao.atividade2.R;
import com.univali.programacao.atividade2.validators.AlturaPesoValidator;

import java.text.DecimalFormat;

public class NumberOneFragment extends Fragment {

    private EditText altura, peso;
    private Button btnCalcularIMC;
    private TextView resultado;
    private AlturaPesoValidator alturaPesoValidator;
    private boolean pesoOk, alturaOk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        alturaPesoValidator = new AlturaPesoValidator();
        View view = inflater.inflate(R.layout.number_one_fragment, container, false);
        altura = view.findViewById(R.id.altura);
        peso = view.findViewById(R.id.peso);
        resultado = view.findViewById(R.id.resultadoIMC);
        btnCalcularIMC = view.findViewById(R.id.btnCalcIMC);
        altura.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>0 && alturaPesoValidator.validate(s.toString())){
                    alturaOk = true;
                }else{
                    alturaOk = false;
                }
                btnCalcularIMC.setEnabled(alturaOk && pesoOk);
            }
        });

        peso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>0 && alturaPesoValidator.validate(s.toString())){
                    pesoOk = true;
                }else{
                    pesoOk = false;
                }
                btnCalcularIMC.setEnabled(alturaOk && pesoOk);
            }
        });
        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Deu certo");
                double alt = convert(altura)/100, p = convert(peso);
                double imc = p / (alt*alt);
                String r = "IMC: "+new DecimalFormat("#0.00").format(imc);
                System.out.println(r);
                if(imc < 20){
                    resultado.setTextColor(getResources().getColor(R.color.purple));
                }else{
                    if(imc>25){
                        resultado.setTextColor(getResources().getColor(R.color.red));
                    }else{
                        resultado.setTextColor(getResources().getColor(R.color.blue));
                    }
                }
                resultado.setText(r);
            }
        });
        return view;
    }

    public double convert(EditText e){
        return Double.parseDouble(e.getText().toString());
    }
}
