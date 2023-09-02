package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.listeners.IngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.squareup.picasso.Picasso

class IngredientiPreferitiAdapter(
    var context: Context,
    var list: ArrayList<Ingredient>,
    var listener: IngredientClickListener,
    var favouritelistener: IngredientFavouriteClickListener,
    var deletelistener: IngredientDeleteClickListener,

    ) : RecyclerView.Adapter<IngredientiPreferitiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientiPreferitiViewHolder {
        return IngredientiPreferitiViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_ingredienti_preferiti, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientiPreferitiViewHolder, position: Int) {
        holder.textView_nome_ingredienti.text = list[position].name
        holder.textView_nome_ingredienti.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/" + list[position].image)
            .into(holder.imageView_ingredienti)

        holder.add_btn.setOnClickListener {
            listener.onClickIngredient(
                list!![holder.adapterPosition].id,
                list!![holder.adapterPosition].name,
                list!![holder.adapterPosition].image
            )
        }

        /*holder.like_btn.setOnClickListener {
            favouritelistener.onClickFavouriteIngredient(
                list!![holder.adapterPosition].id,
                list!![holder.adapterPosition].name,
                list!![holder.adapterPosition].image
            )
        }*/

        holder.delete_btn.setOnClickListener{
            deletelistener.onClickDeleteIngredient(
                list!![holder.adapterPosition].id,
                list!![holder.adapterPosition].name,
                list!![holder.adapterPosition].image
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class IngredientiPreferitiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_quantita_ingredienti: TextView
    var imageView_ingredienti: ImageView
    var textView_nome_ingredienti: TextView
    var add_btn: Button
    var delete_btn:Button

    init {
        textView_quantita_ingredienti = itemView.findViewById(R.id.textView_quantita_ingredienti)
        imageView_ingredienti = itemView.findViewById(R.id.imageView_ingredienti)
        textView_nome_ingredienti = itemView.findViewById(R.id.textView_nome_ingredienti)
        add_btn = itemView.findViewById(R.id.add_btn)
        delete_btn = itemView.findViewById(R.id.delete_btn)
    }
}