package com.example.letmebeyourchef.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.recipeModels.UsedIngredient
import com.squareup.picasso.Picasso

class IngredientiChefAdapter(var context: Context, var list: ArrayList<UsedIngredient>?) :
    RecyclerView.Adapter<IngredientiChefViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientiChefViewHolder {
        return IngredientiChefViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_ricette_chef_ingredients, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientiChefViewHolder, position: Int) {
        holder.textView_ingredient_item.text = list!!.get(position)!!.name
        holder.textView_ingredient_item.isSelected = true

        Log.e(list!![position].image, "LOG")

        Picasso.get()
            .load(list!![position].image)
            .into(holder.imageView_ingredient_items)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}

class IngredientiChefViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var imageView_ingredient_items: ImageView
    var textView_ingredient_item: TextView

    init {
        textView_ingredient_item = itemView.findViewById(R.id.textView_ingredient_item)
        imageView_ingredient_items =
            itemView.findViewById(R.id.imageView_ingredient_items)
    }
}