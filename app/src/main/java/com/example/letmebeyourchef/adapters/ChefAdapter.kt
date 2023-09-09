package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.listeners.AddToCartIngredientClickListener
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRecipesByIngredients
import com.squareup.picasso.Picasso

class ChefAdapter(
    var context: Context,
    var list: List<ResponseFromApiRecipesByIngredients>,
    var listener: RicettaClickListener,
    var addToCartListener: AddToCartIngredientClickListener
    ) : RecyclerView.Adapter<ChefViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChefViewHolder {
            return ChefViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_ricetta_chef, parent, false)
            )
        }
        override fun onBindViewHolder(holder: ChefViewHolder, position: Int) {

            holder.textView_title.text = list!!.get(position)!!.title
            Picasso.get().load(list!![position]!!.image)
                .into(holder.imageView_food)
            holder.textView_having.text = list!!.get(position)!!.usedIngredientCount.toString() + " having"
            holder.textView_likes.text = list!!.get(position)!!.likes.toString() + " likes"
            holder.textView_needed.text = list!!.get(position)!!.missedIngredientCount.toString() + " needed"

            holder.show_btn.setOnClickListener {
                listener.onClickRicetta(
                    list!![holder.adapterPosition].id.toString(),
                    list!![holder.adapterPosition].title.toString(),
                    "",
                    0,
                    0,
                    "",
                    list!![holder.adapterPosition].image.toString(),
                    list!![holder.adapterPosition].imageType.toString(),
                    "",
                    ""
                )
            }

            /*list!![holder.adapterPosition].id.toString(),
            list!![holder.adapterPosition].title.toString(),
            list!![holder.adapterPosition].image.toString(),
            list!![holder.adapterPosition].imageType.toString()*/

            holder.recycler_ricette_ingredienti.setHasFixedSize(true)
            holder.recycler_ricette_ingredienti.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val ingredientiChefAdapter = list!![position].usedIngredients?.let { IngredientiChefAdapter(context, it) }
            holder.recycler_ricette_ingredienti.adapter = ingredientiChefAdapter

            holder.recycler_ricette_ingredienti_mancanti.setHasFixedSize(true)
            holder.recycler_ricette_ingredienti_mancanti.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val ingredientiMancantiChefAdapter = IngredientiMancantiChefAdapter(context, list!![position].missedIngredients, addToCartListener!!)
            holder.recycler_ricette_ingredienti_mancanti.adapter = ingredientiMancantiChefAdapter

        }


        override fun getItemCount(): Int {
            return list?.size ?: 0
        }
    }

    class ChefViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_title: TextView
        var imageView_food: ImageView
        var recycler_ricette_ingredienti: RecyclerView
        var recycler_ricette_ingredienti_mancanti: RecyclerView
        var textView_having: TextView
        var textView_likes: TextView
        var textView_needed: TextView
        var show_btn: Button

        init {
            textView_title = itemView.findViewById(R.id.textView_title)
            imageView_food = itemView.findViewById(R.id.imageView_food)
            recycler_ricette_ingredienti = itemView.findViewById(R.id.recycler_ricette_ingredienti)
            recycler_ricette_ingredienti_mancanti = itemView.findViewById(R.id.recycler_ricette_ingredienti_mancanti)
            textView_having = itemView.findViewById(R.id.textView_having)
            textView_likes = itemView.findViewById(R.id.textView_likes)
            textView_needed = itemView.findViewById(R.id.textView_needed)
            show_btn = itemView.findViewById(R.id.show_btn)

        }
    }
