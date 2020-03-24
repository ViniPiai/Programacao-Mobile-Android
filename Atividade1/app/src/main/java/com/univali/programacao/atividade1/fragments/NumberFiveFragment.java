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

public class NumberFiveFragment extends Fragment {

    private EditText pos11, pos12, pos13, pos21, pos22, pos23, pos31, pos32, pos33;
    private TextView resultado;
    private Button btnCalcularMatriz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_five_fragment, container, false);
        pos11 = view.findViewById(R.id.a11);
        pos12 = view.findViewById(R.id.a12);
        pos13 = view.findViewById(R.id.a13);
        pos21 = view.findViewById(R.id.a21);
        pos22 = view.findViewById(R.id.a22);
        pos23 = view.findViewById(R.id.a23);
        pos31 = view.findViewById(R.id.a31);
        pos32 = view.findViewById(R.id.a32);
        pos33 = view.findViewById(R.id.a33);
        resultado = view.findViewById(R.id.resultadoMatriz);
        btnCalcularMatriz = view.findViewById(R.id.btnCalcularMatriz);
        btnCalcularMatriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a11 = con(pos11), a12 = con(pos12), a13 = con(pos13),
                       a21 = con(pos21), a22 = con(pos22), a23 = con(pos23),
                       a31 = con(pos31), a32 = con(pos32), a33 = con(pos33);
                double diagonal = a11 * a22 * a33;
                resultado.setText("Diagonal: "+diagonal);
            }
        });
        return view;
    }

    private double con(EditText e){
        return Double.parseDouble(e.getText().toString());
    }

}
