package com.univali.programacao.atividade10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BuyOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterBuyOrder adapterBuyOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_order);
        final Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Lista de compras");
        setSupportActionBar(toolbar);
        createRecyclerView();
        Button button = findViewById(R.id.btnBuy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float totalPrice = 0;
                int totalCalories = 0;
                List<Cardapio> cardapios =
                        (List<Cardapio>) getIntent().getSerializableExtra("buyList");
                assert cardapios != null;
                for(Cardapio c : cardapios){
                    totalPrice += c.getPrice() * c.getQuantity();
                    totalCalories += c.getCalories() * c.getQuantity();
                }
                ((TextView)findViewById(R.id.tvResult)).setText("Preço total: "+totalPrice+"\n" +
                        "Calorias total: "+totalCalories);
            }
        });

    }

    private void createRecyclerView(){
        recyclerView = findViewById(R.id.rcBuy);
        adapterBuyOrder = new AdapterBuyOrder(
                (List<Cardapio>) getIntent().getSerializableExtra("buyList"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterBuyOrder.notifyDataSetChanged();
        recyclerView.setAdapter(adapterBuyOrder);
    }
}
