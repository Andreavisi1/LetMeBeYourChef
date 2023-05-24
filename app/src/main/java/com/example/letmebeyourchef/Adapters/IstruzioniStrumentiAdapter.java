package com.example.letmebeyourchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Models.Equipment;
import com.example.letmebeyourchef.Models.Ingredient;
import com.example.letmebeyourchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IstruzioniStrumentiAdapter extends RecyclerView.Adapter<IstruzioniStrumentiViewHolder>{

    Context context;
    List<Equipment> list;

    public IstruzioniStrumentiAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IstruzioniStrumentiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IstruzioniStrumentiViewHolder(LayoutInflater.from(context).inflate(R.layout.list_step_istruzioni_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull IstruzioniStrumentiViewHolder holder, int position) {

        holder.textView_step_istruzioni_item.setText(list.get(position).name);
        holder.textView_step_istruzioni_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/" + list.get(position).image).into(holder.imageView_step_istruzioni_items);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IstruzioniStrumentiViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView_step_istruzioni_items;
    TextView textView_step_istruzioni_item;

    public IstruzioniStrumentiViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_step_istruzioni_item = itemView.findViewById(R.id.textView_step_istruzioni_item);
        imageView_step_istruzioni_items = itemView.findViewById(R.id.imageView_step_istruzioni_items);

    }
}