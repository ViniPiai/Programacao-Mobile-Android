package com.univali.programacao.atividade2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.univali.programacao.atividade2.R;
import com.univali.programacao.atividade2.model.Candidatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NumberTwoFragment extends Fragment {

    private EditText candidato, votos;
    private Button cadastrar;
    private TextView c1, c2, c3, c4, c5, c6, c7, c8;
    private List<Candidatos> candidatos;
    private TextView[] array;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_two_fragment, container, false);
        candidatos = new ArrayList<>();
        candidato = view.findViewById(R.id.nome);
        votos = view.findViewById(R.id.votos);
        cadastrar = view.findViewById(R.id.adicionar);
        c1 = view.findViewById(R.id.cand1);
        c2 = view.findViewById(R.id.cand2);
        c3 = view.findViewById(R.id.cand3);
        c4 = view.findViewById(R.id.cand4);
        c5 = view.findViewById(R.id.cand5);
        c6 = view.findViewById(R.id.cand6);
        c7 = view.findViewById(R.id.cand7);
        c8 = view.findViewById(R.id.cand8);
        c1.setTextColor(getResources().getColor(R.color.blue));
        c2.setTextColor(getResources().getColor(R.color.green));
        array = new TextView[] {c1, c2, c3, c4, c5, c6, c7, c8};
        for(int i=2;i<array.length;i++){
            array[i].setTextColor(getResources().getColor(R.color.gray));
        }
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(candidatos.size() >=8){
                    Toast.makeText(getContext(), "Número máximo de candidatos cadastrados",
                            Toast.LENGTH_SHORT).show();
                }else{
                    candidatos.add(new Candidatos(candidato.getText().toString(),
                            Integer.parseInt(votos.getText().toString())));
                    Collections.sort(candidatos, Collections.<Candidatos>reverseOrder());

                    for(int i=0;i<candidatos.size();i++){
                        Candidatos c = candidatos.get(i);
                        array[i].setText(c.getNome() + " "+c.getVotos());
                    }
                }
            }
        });
        return view;
    }

}
