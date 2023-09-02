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
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.squareup.picasso.Picasso

class CartAdapter(
    var context: Context,
    var list: ArrayList<Ingredient>,
    var deletelistener: IngredientDeleteClickListener,
    ) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_ingredienti_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.textView_nome_ingredienti_cart.text = list[position].name
        holder.textView_nome_ingredienti_cart.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/" + list[position].image)
            .into(holder.imageView_ingredienti_cart)

        holder.bought_btn.setOnClickListener{
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

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView_ingredienti_cart: ImageView
    var textView_nome_ingredienti_cart: TextView
    var bought_btn: Button


    init {
        imageView_ingredienti_cart = itemView.findViewById(R.id.imageView_ingredienti_cart)
        textView_nome_ingredienti_cart = itemView.findViewById(R.id.textView_nome_ingredienti_cart)
        bought_btn = itemView.findViewById(R.id.bought_btn)
    }
}