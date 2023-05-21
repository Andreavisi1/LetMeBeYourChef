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
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteSimili;
import com.example.letmebeyourchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RicetteSimiliAdapter extends RecyclerView.Adapter<RicetteSimiliViewHolder> {

    Context context;
    List<ResponseFromApiRicetteSimili> list;
    RicettaClickListener listener;

    public RicetteSimiliAdapter(Context context, List<ResponseFromApiRicetteSimili> list, RicettaClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RicetteSimiliViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RicetteSimiliViewHolder(LayoutInflater.from(context).inflate((R.layout.list_ricette_simili), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RicetteSimiliViewHolder holder, int position) {
        holder.textView_simili_titolo.setText(list.get(position).title);
        holder.textView_simili_titolo.setSelected(true);
        holder.textView_simili_porzione.setText(list.get(position).servings + " persone");
        Picasso.get().load("https://spoonacular.com/recipeImages/" + list.get(position).id +  "-556x370." + list.get(position).imageType).into(holder.imageView_simili);

        holder.ricette_simili_holder.setOnClickListener(new View.OnClickListener() {
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

class RicetteSimiliViewHolder extends RecyclerView.ViewHolder {
    CardView ricette_simili_holder;
    TextView textView_simili_titolo, textView_simili_porzione;
    ImageView imageView_simili;

    public RicetteSimiliViewHolder(@NonNull View itemView) {
        super(itemView);
        ricette_simili_holder = itemView.findViewById(R.id.ricette_simili_holder);
        textView_simili_titolo = itemView.findViewById(R.id.textView_simili_titolo);
        textView_simili_porzione = itemView.findViewById(R.id.textView_simili_porzione);
        imageView_simili = itemView.findViewById(R.id.imageView_simili);
    }
}