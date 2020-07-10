package com.univali.programacao.atividade10;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterBuyOrder extends RecyclerView.Adapter<AdapterBuyOrder.MyViewHolder> {

    private List<Cardapio> buy;

    AdapterBuyOrder(List<Cardapio> buy) {
        this.buy = buy;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buy_food_adapter, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Cardapio cardapio = buy.get(position);
        holder.name.setText(cardapio.getName());
        holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                buy.get(position).setQuantity(Integer.parseInt(editable.toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return buy.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        EditText quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameBuy);
            quantity = itemView.findViewById(R.id.etQuantity);
        }
    }
}
