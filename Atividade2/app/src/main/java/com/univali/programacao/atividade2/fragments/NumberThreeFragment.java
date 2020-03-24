package com.univali.programacao.atividade2.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.univali.programacao.atividade2.R;
import com.univali.programacao.atividade2.model.Atleta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberThreeFragment extends Fragment {

    private EditText nome, tempo;
    private Button cadastrar, calcular;
    private TextView media, desvio, ganhador;
    private List<Atleta> atletas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_three_fragment, container, false);
        initilizeComponents(view);
        initializeListeners();
        return view;
    }

    private void initilizeComponents(View view){
        nome = view.findViewById(R.id.nome);
        tempo = view.findViewById(R.id.tempo);
        cadastrar = view.findViewById(R.id.AtletaCadastrar);
        calcular = view.findViewById(R.id.calcularTempo);
        media = view.findViewById(R.id.media);
        desvio = view.findViewById(R.id.desvioPadrao);
        ganhador = view.findViewById(R.id.nomeAtleta);
        atletas = new ArrayList<>();
    }

    private void initializeListeners(){
        cadastrar.setOnClickListener(view -> {
            if(atletas.size() <10){
                atletas.add(new Atleta(nome.getText().toString(),
                        Double.parseDouble(tempo.getText().toString())));
            }else{
                Toast.makeText(getContext(), "Número de atletas já está no limite. " +
                        "Já é possível descobrir quem ganhou a partida.", Toast.LENGTH_SHORT)
                        .show();
                calcular.setEnabled(true);
            }
        });
        calcular.setOnClickListener(view -> {
            Collections.sort(atletas, Collections.<Atleta>reverseOrder());
            List<Double> numeros = new ArrayList<>();
            for(Atleta a : atletas){
                numeros.add(a.getTempo());
            }
            double desvioPadro = desvioPadrao(numeros);
            double mediaAritimetica = mediaAritimetica(numeros);
            desvio.setText("Desvio padrão: "+desvioPadro);
            media.setText("Média de tempo: "+mediaAritimetica);
            ganhador.setText("Ganhador: "+atletas.get(0).getNome());
        });
    }

    private double desvioPadrao(List<Double> objetos) {
        if (objetos.size() == 1) {
            return 0.0;
        } else {
            double mediaAritimetica = mediaAritimetica(objetos);
            double somatorio = 0L;
            for (int i = 0; i < objetos.size(); i++) {
                double result = objetos.get(i) - mediaAritimetica;
                somatorio = somatorio + result * result;
            }
            return Math.sqrt(((double) 1 /( objetos.size()-1))
                    * somatorio);
        }
    }

    private double mediaAritimetica(List<Double> objetos) {
        double somatorio = 0l;
        for (Double d : objetos) {
            somatorio += d;
        }
        return somatorio / objetos.size();
    }
}
