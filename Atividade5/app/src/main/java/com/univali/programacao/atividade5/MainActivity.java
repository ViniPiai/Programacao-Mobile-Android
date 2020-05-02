package com.univali.programacao.atividade5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.security.PrivilegedAction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private LinearLayout l;
    private final Integer INITIAL_HOUR = 8;
    private final Integer FINAL_HOUR = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.linearLayout);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int count = 0;
        List<Integer> hours = new ArrayList<>();
        if(hour>=8 && hour<=FINAL_HOUR) {
            for (int i = 0; i <=((FINAL_HOUR-INITIAL_HOUR) - (FINAL_HOUR - hour)); i++) {
                if(8+i!=13){
                    hours.add(8+i);
                }
            }
        }
        Log.d("Vin", "V");
        for(Integer i : hours){
            Log.d("Lista", String.valueOf(i));
        }
        for(int i=0;i<hours.size();i++){
            EditText editText = new EditText(l.getContext());
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(p);
            editText.setHint(String.valueOf(hours.get(i)));
            editText.setId(i);
            l.addView(editText);
        }

    }
}
