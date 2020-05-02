package com.example.infoscidadessc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        List<Cidade> cidades = new ArrayList<>();
        Cidade cidade1 = new Cidade("Adson Batista", "2.656", "11,27");
        cidades.add(cidade1);
        Cidade cidade2 = new Cidade("Aberlado Luz", "17.584", "16,66");
        cidades.add(cidade2);
        Cidade cidade3 = new Cidade("Água Doce", "7.110", "5,42");
        cidades.add(cidade3);
        Cidade cidade4 = new Cidade("Águas Mornas", "5.926", "16,42");
        cidades.add(cidade4);
        Cidade cidade5 = new Cidade("Alfredo Wagner", "9,737", "13,29");
        cidades.add(cidade5);
        Cidade cidade6 = new Cidade("Angelina", "5.155", "10,33");
        cidades.add(cidade6);
        Cidade cidade7 = new Cidade("Anita Garibaldi", "8.230", "13,98");
        cidades.add(cidade7);
        Cidade cidade8 = new Cidade("Anitápolis", "3.259", "6,00");
        cidades.add(cidade8);
        Cidade cidade9 = new Cidade("Antonio Carlos", "7.906", "34,5");
        cidades.add(cidade9);
        Cidade cidade10 = new Cidade("Araquari", "29.593", "73,54");
        cidades.add(cidade10);
        Cidade cidade11 = new Cidade("Armazém", "8.159", "47,03");
        cidades.add(cidade11);
        Cidade cidade12 = new Cidade("Arrio Trinta", "3.562", "37,75");
        cidades.add(cidade12);
        Cidade cidade13 = new Cidade("Balneário Arroio do Silva", "10.876", "115,92");
        cidades.add(cidade13);
        Cidade cidade14 = new Cidade("Balneário Barra do Sul", "9.330", "84,48");
        cidades.add(cidade14);
        Cidade cidade15 = new Cidade("Balneário Camboriú", "142.295", "2601,17");
        cidades.add(cidade15);
        Cidade cidade16 = new Cidade("Balneário Gaivota", "9.259", "62,68");
        cidades.add(cidade16);
        Cidade cidade17 = new Cidade("Balneário Piçarras", "19.329", "195,10");
        cidades.add(cidade17);
        Cidade cidade18 = new Cidade("Balneário Rincão", "11.628", "200,89");
        cidades.add(cidade18);
        Cidade cidade19 = new Cidade("Barra Velha", "24.943", "177,96");
        cidades.add(cidade19);
        Cidade cidade20 = new Cidade("Biguaçu", "68.481", "192,23");
        cidades.add(cidade20);
        Cidade cidade21 = new Cidade("Blumenau", "357.199", "633,09");
        cidades.add(cidade21);
        Cidade cidade22 = new Cidade("Bombinhas", "16.311", "472,93");
        cidades.add(cidade22);
        Cidade cidade23 = new Cidade("Caçador", "78.595", "73,60");
        cidades.add(cidade23);
        Cidade cidade24 = new Cidade("Camboriú", "82.989", "293,62");
        cidades.add(cidade24);
        Cidade cidade25 = new Cidade("Campos Novos", "35.710", "21,52");
        cidades.add(cidade25);
        Cidade cidade26 = new Cidade("Canelinha", "11.781", "77,81");
        cidades.add(cidade26);
        Cidade cidade27 = new Cidade("Canoinhas", "52.765", "46,09");
        cidades.add(cidade27);
        Cidade cidade28 = new Cidade("Chapecó", "220.367", "316,56");
        cidades.add(cidade28);
        Cidade cidade29 = new Cidade("Dionísio Cerqueira", "15.450", "40,91");
        cidades.add(cidade29);
        Cidade cidade30 = new Cidade("Erval Velho", "4.353", "20,96");
        cidades.add(cidade30);
        Cidade cidade31 = new Cidade("Florianópolis", "500.973", "671,12");
        cidades.add(cidade31);
        Cidade cidade32 = new Cidade("Fraiburgo", "36.443", "0,07");
        cidades.add(cidade32);
        Cidade cidade33 = new Cidade("Garopaba", "18.144", "158,23");
        cidades.add(cidade33);
        Cidade cidade34 = new Cidade("Governador Celso Ramos", "13.655", "116,53");
        cidades.add(cidade34);
        Cidade cidade35 = new Cidade("Herval d'Oeste", "21.233", "95,47");
        cidades.add(cidade35);
        Cidade cidade36 = new Cidade("Içara", "52.284", "228,39");
        cidades.add(cidade36);
        Cidade cidade37 = new Cidade("Imbituba", "44.412", "240,34");
        cidades.add(cidade37);
        Cidade cidade38 = new Cidade("Jaguaruna", "16.418", "48,7");
        cidades.add(cidade38);
        Cidade cidade39 = new Cidade("Jaraguá do Sul", "177.697", "278,55");
        cidades.add(cidade39);
        Cidade cidade40 = new Cidade("Laguna", "44.982", "133,72");
        cidades.add(cidade40);
        Cidade cidade41 = new Cidade("Lauro Müller", "14.426", "53,33");
        cidades.add(cidade41);
        Cidade cidade42 = new Cidade("Mafra", "56.017", "39,9");
        cidades.add(cidade42);
        Cidade cidade43 = new Cidade("Matos Costa", "2.839", "6,56");
        cidades.add(cidade43);
        Cidade cidade44 = new Cidade("Navegantes", "81.475", "633,09");
        cidades.add(cidade44);
        Cidade cidade45 = new Cidade("Orleans", "22.311", "40,58");
        cidades.add(cidade45);
        Cidade cidade46 = new Cidade("Palhoça", "171.797", "425,83");
        cidades.add(cidade46);
        Cidade cidade47 = new Cidade("Rancho Queimado", "2.748", "9,59");
        cidades.add(cidade47);
        Cidade cidade48 = new Cidade("Santiago do Sul", "1.260", "18,88");
        cidades.add(cidade48);
        Cidade cidade49 = new Cidade("Tijucas", "36.170", "130,76");
        cidades.add(cidade49);
        Cidade cidade50 = new Cidade("Videira", "53.065", "138,97");
        cidades.add(cidade50);


        ListView listViewCidades = (ListView) findViewById(R.id.listViewCidades);
        SearchView searchViewCidades = (SearchView) findViewById(R.id.searchViewCidades);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setActionBar(toolbar);

        final ArrayAdapter<Cidade> adapter = new ArrayAdapter<Cidade>(MainActivity.this, android.R.layout.simple_list_item_1, cidades);
        listViewCidades.setAdapter(adapter);

        listViewCidades.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = parent.getItemAtPosition(position);
                Cidade cidade = (Cidade) o;
                Log.d("Teste", cidade.toString());
                Intent myIntent = new Intent (getApplicationContext(), InfosCidadesActivity.class);
                myIntent.putExtra("Cidade", cidade);
                startActivity(myIntent);
            }
        });

        searchViewCidades.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}











