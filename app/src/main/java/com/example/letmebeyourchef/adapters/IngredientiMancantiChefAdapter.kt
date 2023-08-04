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
import com.example.letmebeyourchef.recipeModels.MissedIngredient
import com.squareup.picasso.Picasso

class IngredientiMancantiChefAdapter(var context: Context, var list: ArrayList<MissedIngredient>?, var listener: AddToCartIngredientClickListener) :
    RecyclerView.Adapter<IngredientiMancantiChefViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientiMancantiChefViewHolder {
        return IngredientiMancantiChefViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_ricette_chef_ingredients_missing, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientiMancantiChefViewHolder, position: Int) {
        holder.textView_ingredient_missing_item.text = list!!.get(position)!!.name
        holder.textView_ingredient_missing_item.isSelected = true
        Picasso.get()
            .load(list!![position].image)
            .into(holder.imageView_ingredient_missing_items)
        holder.cart_btn.setOnClickListener {
            listener.onClickAddToCartIngredient(
                list!![holder.adapterPosition].id,
                list!![holder.adapterPosition].name.toString(),
                list!![holder.adapterPosition].image.toString())
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}

class IngredientiMancantiChefViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var imageView_ingredient_missing_items: ImageView
    var textView_ingredient_missing_item: TextView
    var cart_btn: Button

    init {
        textView_ingredient_missing_item = itemView.findViewById(R.id.textView_ingredient_missing_item)
        imageView_ingredient_missing_items =
            itemView.findViewById(R.id.imageView_ingredient_missing_items)
        cart_btn = itemView.findViewById(R.id.cart_btn)

    }
}