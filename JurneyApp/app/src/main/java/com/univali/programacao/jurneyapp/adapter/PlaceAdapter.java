package com.univali.programacao.jurneyapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.univali.programacao.jurneyapp.R;
import com.univali.programacao.jurneyapp.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {


    private List<Place> places;
    private List<Place> placesForAdd;

    public PlaceAdapter(List<Place> places, List<Place> placesForAdd) {
        this.places = places;
        this.placesForAdd = placesForAdd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_place, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Place place = places.get(position);
        holder.tvName.setText(place.getName());
        holder.tvDescription.setText(place.getDescription());
        holder.imageView.setImageBitmap(place.getImageBitmap());
        holder.cbAdd.setTag(position);
        holder.cbAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Log.i("Teste", "Está selecionado");
                    placesForAdd.add(place);
                }else{
                    Log.i("Teste", "Não está selecionado");
                    placesForAdd.remove(place);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDescription;
        ImageView imageView;
        CheckBox cbAdd;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.imageView);
            cbAdd = itemView.findViewById(R.id.cbAdd);
        }
    }
}
