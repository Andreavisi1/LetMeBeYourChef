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
import com.example.letmebeyourchef.listeners.AddToCartIngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.squareup.picasso.Picasso

class StorageAdapter(
    var context: Context,
    var list: ArrayList<Ingredient>,
    var addToCartlistener: AddToCartIngredientClickListener,
    var deletelistener: IngredientDeleteClickListener
    ) : RecyclerView.Adapter<StorageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageViewHolder {
        return StorageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_ingredienti_storage, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        holder.textView_nome_ingredienti.text = list[position].name
        holder.textView_nome_ingredienti.isSelected = true
        holder.textView_quantita_ingredienti.isSelected = true

        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/" + list[position].image)
            .into(holder.imageView_ingredienti)

        holder.cart_btn.setOnClickListener{
            addToCartlistener.onClickAddToCartIngredient(
                list!![holder.adapterPosition].id,
                list!![holder.adapterPosition].name,
                list!![holder.adapterPosition].image
            )
        }

        holder.rem_btn.setOnClickListener{
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

class StorageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_quantita_ingredienti: TextView
    var imageView_ingredienti: ImageView
    var textView_nome_ingredienti: TextView
    var cart_btn: Button
    var rem_btn: Button

    init {
        textView_quantita_ingredienti = itemView.findViewById(R.id.textView_quantita_ingredienti)
        imageView_ingredienti = itemView.findViewById(R.id.imageView_ingredienti)
        textView_nome_ingredienti = itemView.findViewById(R.id.textView_nome_ingredienti)
        cart_btn = itemView.findViewById(R.id.cart_btn)
        rem_btn = itemView.findViewById(R.id.rem_btn)
    }
}