package com.univali.programacao.atividade7_agendapessoal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.univali.programacao.atividade7_agendapessoal.R;
import com.univali.programacao.atividade7_agendapessoal.dao.DAOPersonalAgenda;
import com.univali.programacao.atividade7_agendapessoal.model.PersonalAgenda;

import java.util.List;

public class AdapterPersonalAgenda extends RecyclerView.Adapter<AdapterPersonalAgenda.MyViewHolder> {

    private List<PersonalAgenda> personalAgendaList;
    private Context context;

    public AdapterPersonalAgenda(List<PersonalAgenda> personalAgendaList, Context context) {
        this.personalAgendaList = personalAgendaList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_personal_agenda, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final PersonalAgenda personalAgenda = personalAgendaList.get(position);
        holder.name.setText(personalAgenda.getName());
        holder.description.setText(personalAgenda.getDescription());
        holder.date.setText(personalAgenda.getDate().toString());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOPersonalAgenda.deleteOne(personalAgenda.getId(), context);
                personalAgendaList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, personalAgendaList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return personalAgendaList.size();
    }

    public void filterList(List<PersonalAgenda> filteredList) {
        personalAgendaList = filteredList;
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView date;
        TextView remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            description = itemView.findViewById(R.id.tvDescription);
            date = itemView.findViewById(R.id.tvDate);
            remove = itemView.findViewById(R.id.tvDelete);
        }
    }
}
