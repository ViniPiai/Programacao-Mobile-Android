package com.univali.programacao.atividade3.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.univali.programacao.atividade3.R;

import java.util.Calendar;

public class Number3Fragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.number_3_fragment, container, false);
        FrameLayout f = view.findViewById(R.id.frameId3);
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        if(hour>=18 && hour<=6){
            Toast.makeText(getContext(), "Boa noite", Toast.LENGTH_LONG).show();
            f.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        }else{
            if(hour<12){
                Toast.makeText(getContext(), "Bom dia", Toast.LENGTH_LONG).show();
                f.setBackgroundColor(getResources().getColor(R.color.yellow));
            }else{
                if(hour>=12){
                    Toast.makeText(getContext(), "Boa tarde", Toast.LENGTH_LONG).show();
                    f.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }
        }

        return view;
    }
}
