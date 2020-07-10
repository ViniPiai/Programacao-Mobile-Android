package com.univali.programacao.jurneyapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.univali.programacao.jurneyapp.R;
import com.univali.programacao.jurneyapp.adapter.PlaceAdapter;
import com.univali.programacao.jurneyapp.db.JourneyDB;
import com.univali.programacao.jurneyapp.model.Place;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NewJorneyListActivity extends AppCompatActivity {

    private List<Place> places = new ArrayList<>();
    private List<Place> placesForAdd = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jorney_list);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Nova lista de viajem");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        new ExternalJourneyList().execute();
        final EditText tvListName = findViewById(R.id.etListName);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listName = tvListName.getText().toString();
                if(!listName.equals("")){
                    if(placesForAdd.size() > 0){
                        JourneyDB.insert(listName, placesForAdd, getApplicationContext());
                        onBackPressed();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Você deve escolher pelo menos um local.",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Você deve digitar um nome para a lista.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rvPlaces);
        PlaceAdapter placeAdapter = new PlaceAdapter(places, placesForAdd);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(placeAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    class ExternalJourneyList extends AsyncTask<String, Integer, List<Place>> {

        @Override
        protected List<Place> doInBackground(String... strings) {
            List<Place> places = new ArrayList<>();

            try {
                String resourceURI = "https://raw.githubusercontent.com/ViniPiai/Places-mobile/master/places.xml";
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
                NodeList nList = doc.getElementsByTagName("place");

                for (int i=0;i<nList.getLength();i++){
                    Node node = nList.item(i);
                    Element e = (Element) node;
                    String name = valueFromNode(e, "title");
                    String description = valueFromNode(e, "description");
                    String imageUrl = valueFromNode(e, "image");
                    Log.i("Name", name);

                    URL imageURL = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
                    connection.setRequestMethod("GET");

                    places.add(new Place(
                            name, description, imageUrl,
                            BitmapFactory.decodeStream(connection.getInputStream())
                    ));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return places;
        }

        @Override
        protected void onPostExecute(List<Place> placeList) {
            if(placeList != null){
                Log.i("A", String.valueOf(placeList.size()));
                places.addAll(placeList);
                Log.i("Tamanho do places: ", String.valueOf(places.size()));
                loadRecyclerView();
            }else{
                Toast.makeText(getApplicationContext(), "Deu errado", Toast.LENGTH_LONG)
                        .show();
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
