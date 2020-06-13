package com.univali.programacao.atividade10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private List<Cardapio> cardapioArrayList = new ArrayList<>();
    private List<Cardapio> buyCardapio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cardápio");
        setSupportActionBar(toolbar);
        new ExternalFoodMenuReaderAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {
            Log.i("Size", String.valueOf(buyCardapio.size()));
            for (Cardapio c : buyCardapio) {
                Log.i(c.getName(), c.getDescription());
            }
            Intent intent = new Intent(getApplicationContext(), BuyOrder.class);
            intent.putExtra("buyList", (Serializable) buyCardapio);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rvMain);
        AdapterFoodMenu adapterFoodMenu = new AdapterFoodMenu(cardapioArrayList, buyCardapio, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterFoodMenu.notifyDataSetChanged();
        recyclerView.setAdapter(adapterFoodMenu);
    }

    @SuppressLint("StaticFieldLeak")
    class ExternalFoodMenuReaderAsyncTask extends AsyncTask<String, Integer, List<Cardapio>> {

        @Override
        protected List<Cardapio> doInBackground(String... params) {

            List<Cardapio> cardapio = new ArrayList<>();

            try {
                String resourceURI = "https://www.w3schools.com/xml/simple.xml";
                String httpParameters = "";//?id=0
                String formatedURL = resourceURI + httpParameters;
                URL url = new URL(formatedURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream is = con.getInputStream();

                //parse XML document

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);

                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                NodeList nList = doc.getElementsByTagName("food");

                for (int i=0;i<nList.getLength();i++){
                    Node node = nList.item(i);
                    Element e = (Element) node;
                    String name = valueFromNode(e, "name");
                    String price = valueFromNode(e, "price");
                    String description = valueFromNode(e, "description");
                    String calories = valueFromNode(e, "calories");

                    cardapio.add(new Cardapio(name, description,
                            Float.parseFloat(price.replace("$", "")),
                            Integer.parseInt(calories)));
                }
            } catch (Exception e) {
                Log.e("Exception", e.getMessage());
            }
            return cardapio;
        }

        @Override
        protected void onPostExecute(List<Cardapio> cardapios) {
            if(cardapios != null){
                Log.i("A", String.valueOf(cardapios.size()));
                cardapioArrayList.addAll(cardapios);
                createRecyclerView();
            }else{
                Toast.makeText(getApplicationContext(), "Olá", Toast.LENGTH_LONG).show();
            }
        }

        private String valueFromNode(Element e, String node){
            NodeList nodeList = e.getElementsByTagName(node);
            Element element = (Element) nodeList.item(0);
            nodeList = element.getChildNodes();
            return  (nodeList.item(0)).getNodeValue();
        }
    }
}
