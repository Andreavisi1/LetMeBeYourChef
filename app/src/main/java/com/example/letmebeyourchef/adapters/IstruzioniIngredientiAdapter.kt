package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.squareup.picasso.Picasso

class IstruzioniIngredientiAdapter constructor(var context: Context, var list: List<Ingredient?>?) :
    RecyclerView.Adapter<IstruzioniIngredientiViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IstruzioniIngredientiViewHolder {
        return IstruzioniIngredientiViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_step_istruzioni_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IstruzioniIngredientiViewHolder, position: Int) {
        holder.textView_step_istruzioni_item.text = list!!.get(position)!!.name
        holder.textView_step_istruzioni_item.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/" + list!!.get(position)!!.image)
            .into(holder.imageView_step_istruzioni_items)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}

class IstruzioniIngredientiViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var imageView_step_istruzioni_items: ImageView
    var textView_step_istruzioni_item: TextView

    init {
        textView_step_istruzioni_item = itemView.findViewById(R.id.textView_step_istruzioni_item)
        imageView_step_istruzioni_items =
            itemView.findViewById(R.id.imageView_step_istruzioni_items)
    }
}