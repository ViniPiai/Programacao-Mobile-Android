package com.univali.programacao.atividade7_agendapessoal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.univali.programacao.atividade7_agendapessoal.R;
import com.univali.programacao.atividade7_agendapessoal.adapter.AdapterPersonalAgenda;
import com.univali.programacao.atividade7_agendapessoal.dao.DAOPersonalAgenda;
import com.univali.programacao.atividade7_agendapessoal.model.PersonalAgenda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPersonalAgenda adapterPersonalAgenda;
    private List<PersonalAgenda> personalAgendaList;
    private Boolean isSearchByName = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Agenda Pessoal");
        setSupportActionBar(toolbar);
        createRecyclerView();
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbName:
                        isSearchByName=true;
                        break;
                    case R.id.rbDescription:
                        isSearchByName=false;
                }
            }
        });
        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isSearchByName){
                    filterByName(editable.toString());
                }else{
                    filterByDescription(editable.toString());
                }
            }
        });
    }

    private void filterByName(String value){
        List<PersonalAgenda> personalAgendaList1 = new ArrayList<>();
        for(PersonalAgenda personalAgenda : personalAgendaList){
            if(personalAgenda.getName().contains(value)){
                personalAgendaList1.add(personalAgenda);
            }
        }
        adapterPersonalAgenda.filterList(personalAgendaList1);
    }

    private void filterByDescription(String value){
        List<PersonalAgenda> personalAgendaList1 = new ArrayList<>();
        for(PersonalAgenda personalAgenda : personalAgendaList){
            if(personalAgenda.getDescription().contains(value)){
                personalAgendaList1.add(personalAgenda);
            }
        }
        adapterPersonalAgenda.filterList(personalAgendaList1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        createRecyclerView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        createRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addItem:
                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
                break;
            case R.id.cleanItem:
                Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void createRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        personalAgendaList = DAOPersonalAgenda.getAll(getApplicationContext());
        Collections.sort(personalAgendaList, new Comparator<PersonalAgenda>() {
            @Override
            public int compare(PersonalAgenda personalAgenda, PersonalAgenda t1) {
                return stringCompare(personalAgenda.getDate(), t1.getDate());
            }
        });
        /*personalAgendaList.add(new PersonalAgenda(56, "Comprar pão",
                "Atividade de ir comprar pão na padaria albanás", "04/05/2020"));*/
        adapterPersonalAgenda = new AdapterPersonalAgenda(personalAgendaList, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterPersonalAgenda.notifyDataSetChanged();
        recyclerView.setAdapter(adapterPersonalAgenda);
    }

    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }
}
