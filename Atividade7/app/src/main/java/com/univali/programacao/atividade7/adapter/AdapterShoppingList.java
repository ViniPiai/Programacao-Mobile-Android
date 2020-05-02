package com.univali.programacao.atividade7.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.univali.programacao.atividade7.R;
import com.univali.programacao.atividade7.dao.ShoppingLisDAO;
import com.univali.programacao.atividade7.helper.DbHelper;
import com.univali.programacao.atividade7.model.ShoppingList;

import java.security.acl.LastOwnerException;
import java.util.List;

public class AdapterShoppingList extends RecyclerView.Adapter<AdapterShoppingList.MyViewHolder> {

    private List<ShoppingList> shoppingListList;
    private Context context;

    public AdapterShoppingList(List<ShoppingList> shoppingListList, Context context) {
        this.shoppingListList = shoppingListList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterShoppingList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_shopping_list, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ShoppingList shoppingList = shoppingListList.get(position);
        holder.product.setText(shoppingList.getProduct());
        holder.quantity.setText(shoppingList.getQuantity());
        holder.type.setText(shoppingList.getType());
        holder.checkBox.setChecked(shoppingList.getPurchased());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingLisDAO.purchase(context, shoppingList.getId(), holder.checkBox.isChecked());
                Log.i("INFO DB", "Trocou");
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingListList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product;
        TextView quantity;
        TextView type;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.tvProduct);
            quantity = itemView.findViewById(R.id.tvQuantity);
            type = itemView.findViewById(R.id.tvType);
            checkBox = itemView.findViewById(R.id.cbPurchased);
        }
    }

}
