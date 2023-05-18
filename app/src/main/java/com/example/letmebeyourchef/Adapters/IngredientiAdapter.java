package com.example.letmebeyourchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Models.ExtendedIngredient;
import com.example.letmebeyourchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientiAdapter extends RecyclerView.Adapter<IngredientiViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientiAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientiViewHolder(LayoutInflater.from(context).inflate((R.layout.list_ingredienti_ricette), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientiViewHolder holder, int position) {
        holder.textView_nome_ingredienti.setText(list.get(position).name);
        holder.textView_nome_ingredienti.setSelected(true);
        holder.textView_quantità_ingredienti.setText(list.get(position).original);
        holder.textView_quantità_ingredienti.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_ingredienti);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientiViewHolder extends RecyclerView.ViewHolder {
    TextView textView_quantità_ingredienti, textView_nome_ingredienti;
    ImageView imageView_ingredienti;
    public IngredientiViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_quantità_ingredienti = itemView.findViewById(R.id.textView_quantità_ingredienti);
        textView_nome_ingredienti = itemView.findViewById(R.id.textView_nome_ingredienti);
        imageView_ingredienti = itemView.findViewById(R.id.imageView_ingredienti);

    }
}
