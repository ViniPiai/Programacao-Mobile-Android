package com.example.tripmanager;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TripList {
    ArrayList<Location> locations = new ArrayList<Location>();

    public TripList() throws ExecutionException, InterruptedException {
        String result = new InternalAsyncTaskClass().execute().get();
    }

    public ArrayList<Location> getLocations(){return locations;}

    class InternalAsyncTaskClass extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL("https://raw.githubusercontent.com/ViniPiai/Places-mobile/master/places.xml");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStream in = null;
            try {
                in = url.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;
            try {
                doc = documentBuilder.parse(in);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            NodeList placesList = doc.getElementsByTagName("place");

            for (int i = 0; i < placesList.getLength(); i++) {
                Node place = placesList.item(i);
                if (place.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) place;
                    String nome = getValue("title", element);
                    String imageUrl = getValue("image", element);
                    String description = getValue("description", element);
//                    System.out.println(nome);
//                    System.out.println(imageUrl);
//                    System.out.println(description);
                    Location new_location = new Location(nome, imageUrl, description);
                    locations.add(new_location);
                }
            }
            return "";
        }

        public String getValue(String tag, Element element){
            NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = (Node) nodes.item(0);
            return node.getNodeValue();
        }
    }

}
