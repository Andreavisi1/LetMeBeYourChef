package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.recipeModels.ExtendedIngredient
import com.squareup.picasso.Picasso

class IngredientiRicettaAdapter(var context: Context, var list: ArrayList<ExtendedIngredient>) :
    RecyclerView.Adapter<IngredientiRicettaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientiRicettaViewHolder {
        return IngredientiRicettaViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_ingredienti_ricette, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientiRicettaViewHolder, position: Int) {
        holder.textView_nome_ingredienti.text = list[position].name
        holder.textView_nome_ingredienti.isSelected = true
        holder.textView_quantita_ingredienti.text = list[position].original
        holder.textView_quantita_ingredienti.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/" + list[position].image)
            .into(holder.imageView_ingredienti)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class IngredientiRicettaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_quantita_ingredienti: TextView
    var textView_nome_ingredienti: TextView
    var imageView_ingredienti: ImageView

    init {
        textView_quantita_ingredienti = itemView.findViewById(R.id.textView_quantita_ingredienti)
        textView_nome_ingredienti = itemView.findViewById(R.id.textView_nome_ingredienti)
        imageView_ingredienti = itemView.findViewById(R.id.imageView_ingredienti)
    }
}