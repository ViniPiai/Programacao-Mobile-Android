package com.univali.programacao.atividade10;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterFoodMenu extends RecyclerView.Adapter<AdapterFoodMenu.MyViewHolder> {

    private List<Cardapio> cardapios;
    private List<Cardapio> buyCardapios;
    private Context context;
    private boolean[] checked;

    public AdapterFoodMenu(List<Cardapio> cardapios, List<Cardapio> buyCardapios, Context context) {
        this.cardapios = cardapios;
        this.buyCardapios = buyCardapios;
        this.context = context;
        checked = new boolean[cardapios.size()];
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_menu_item, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Cardapio cardapio = cardapios.get(position);
        holder.name.setText(cardapio.getName());
        holder.description.setText(cardapio.getDescription());
        holder.price.setText("$"+ cardapio.getPrice());
        holder.calories.setText(String.valueOf(cardapio.getCalories()));
        holder.buy.setTag(position);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.buy.isChecked()){
                    buyCardapios.add(cardapio);
                    Log.i(cardapio.getName(), "Incluido");
                }else{
                    buyCardapios.remove(cardapio);
                    Log.i(cardapio.getName(), "Retirado");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardapios.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView price;
        TextView calories;
        CheckBox buy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            description = itemView.findViewById(R.id.tvDescription);
            price = itemView.findViewById(R.id.tvPrice);
            calories = itemView.findViewById(R.id.tvCalories);
            buy = itemView.findViewById(R.id.cbBuy);
        }
    }
}
