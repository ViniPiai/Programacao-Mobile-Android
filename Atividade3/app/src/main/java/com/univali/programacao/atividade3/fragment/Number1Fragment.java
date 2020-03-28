package com.univali.programacao.atividade3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.univali.programacao.atividade3.R;
import com.univali.programacao.atividade3.activity.FactorialResultActivity;

public class Number1Fragment extends Fragment {

    private EditText fatorial;
    private Button calcular;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_1_fragment, container, false);
        fatorial = view.findViewById(R.id.fatorialEB);
        calcular = view.findViewById(R.id.btnCalcularFatorial);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FactorialResultActivity.class);
                long fatorialResult = 1;
                if(Integer.parseInt(fatorial.getText().toString()) >0) {
                    for (long i = 1; i <= Integer.parseInt(fatorial.getText().toString()); i++) {
                        fatorialResult *= i;
                        Log.d("Teste", String.valueOf(fatorialResult));
                    }
                }else{
                    fatorialResult = 0;
                }

                intent.putExtra("fatorial", String.valueOf(fatorialResult));
                startActivity(intent);
            }
        });
        return view;
    }
}
