package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ListRicettePreferiteBinding
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.recipeModels.Recipe
import com.squareup.picasso.Picasso

class RicettePreferiteAdapter(
    var context: Context,
    var list: List<Recipe?>?,
    var listener: RicettaClickListener
    ) : RecyclerView.Adapter<RicetteRandomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RicetteRandomViewHolder {
            return RicetteRandomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_ricette_random, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RicetteRandomViewHolder, position: Int) {
            holder.textView_title.text = list!!.get(position)!!.title
            holder.textView_title.isSelected = true
            holder.textView_likes.text = list!!.get(position)!!.aggregateLikes.toString() + " likes"
            holder.textView_porzioni.text = list!!.get(position)!!.servings.toString() + " portions"
            holder.textView_tempo.text = (list!!.get(position)!!.readyInMinutes.toString() + " minutes")
            Picasso.get().load(list!![position]!!.image).into(holder.imageView_food)
            holder.random_list_container.setOnClickListener(View.OnClickListener {
                listener.onClickRicetta(
                    list!![holder.adapterPosition]!!.id.toString()
                )
            })
        }

        override fun getItemCount(): Int {
            return list!!.size
        }
    }

    class RicettePreferiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var random_list_container: CardView
        var textView_title: TextView
        var textView_porzioni: TextView
        var textView_likes: TextView
        var textView_tempo: TextView
        var imageView_food: ImageView

        init {
            random_list_container = itemView.findViewById(R.id.random_list_container)
            textView_title = itemView.findViewById(R.id.textView_title)
            textView_porzioni = itemView.findViewById(R.id.textView_porzioni)
            textView_likes = itemView.findViewById(R.id.textView_likes)
            textView_tempo = itemView.findViewById(R.id.textView_tempo)
            imageView_food = itemView.findViewById(R.id.imageView_food)
        }
    }