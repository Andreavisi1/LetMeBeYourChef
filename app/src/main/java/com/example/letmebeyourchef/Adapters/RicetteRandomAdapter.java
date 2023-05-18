package com.example.letmebeyourchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Listeners.RicettaClickListener;
import com.example.letmebeyourchef.Models.Recipe;
import com.example.letmebeyourchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RicetteRandomAdapter extends RecyclerView.Adapter<RicetteRandomViewHolder>{
    Context context;
    List<Recipe> list;
    RicettaClickListener listener;

    public RicetteRandomAdapter(Context context, List<Recipe> list, RicettaClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RicetteRandomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RicetteRandomViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ricette_random, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RicetteRandomViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_likes.setText(list.get(position).aggregateLikes + " likes");
        holder.textView_porzioni.setText(list.get(position).servings + " porzioni");
        holder.textView_tempo.setText((list.get(position).readyInMinutes + " minuti"));
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickRicetta(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RicetteRandomViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView textView_title, textView_porzioni, textView_likes, textView_tempo;
    ImageView imageView_food;



    public RicetteRandomViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_porzioni = itemView.findViewById(R.id.textView_porzioni);
        textView_likes = itemView.findViewById(R.id.textView_likes);
        textView_tempo = itemView.findViewById(R.id.textView_tempo);
        imageView_food = itemView.findViewById(R.id.imageView_food);
    }
}
