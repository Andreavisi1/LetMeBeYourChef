package com.example.letmebeyourchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Models.Step;
import com.example.letmebeyourchef.R;

import java.util.List;

public class IstruzioniStepAdapter extends RecyclerView.Adapter<IstruzioniStepViewHolder> {

    Context context;
    List<Step> list;

    public IstruzioniStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IstruzioniStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IstruzioniStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_step_istruzioni, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IstruzioniStepViewHolder holder, int position) {

        holder.textView_numero_step_istruzioni.setText(String.valueOf(list.get(position).number));
        holder.textView_titolo_step_istruzioni.setText(list.get(position).step);

        holder.recycler_istruzioni_ingredienti.setHasFixedSize(true);
        holder.recycler_istruzioni_ingredienti.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        IstruzioniIngredientiAdapter istruzioniIngredientiAdapter = new IstruzioniIngredientiAdapter(context, list.get(position).ingredients);
        holder.recycler_istruzioni_ingredienti.setAdapter(istruzioniIngredientiAdapter);

        holder.recycler_istruzioni_strumenti.setHasFixedSize(true);
        holder.recycler_istruzioni_strumenti.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        IstruzioniStrumentiAdapter istruzioniStrumentiAdapter = new IstruzioniStrumentiAdapter(context, list.get(position).equipment);
        holder.recycler_istruzioni_strumenti.setAdapter(istruzioniStrumentiAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IstruzioniStepViewHolder extends RecyclerView.ViewHolder{
    TextView textView_numero_step_istruzioni, textView_titolo_step_istruzioni;
    RecyclerView recycler_istruzioni_strumenti, recycler_istruzioni_ingredienti;


    public  IstruzioniStepViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_numero_step_istruzioni = itemView.findViewById(R.id.textView_numero_step_istruzioni);
        textView_titolo_step_istruzioni = itemView.findViewById(R.id.textView_titolo_step_istruzioni);
        recycler_istruzioni_strumenti = itemView.findViewById(R.id.recycler_istruzioni_strumenti);
        recycler_istruzioni_ingredienti = itemView.findViewById(R.id.recycler_istruzioni_ingredienti);

    }
}
