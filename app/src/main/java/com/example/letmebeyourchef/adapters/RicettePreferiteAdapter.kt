package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.listeners.RicettaDeleteClickListener
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.squareup.picasso.Picasso

class RicettePreferiteAdapter(
    var context: Context,
    var list: List<FavouriteRecipe>?,
    var listener: RicettaClickListener,
    var deleteListener: RicettaDeleteClickListener
    ) : RecyclerView.Adapter<RicettePreferiteViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RicettePreferiteViewHolder {
            return RicettePreferiteViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_ricette_preferite, parent, false)
            )
        }
        override fun onBindViewHolder(holder: RicettePreferiteViewHolder, position: Int) {
            holder.textView_title.text = list!!.get(position).title
            holder.textView_title.isSelected = true
            holder.textView_likes.text = list!!.get(position).aggregateLikes.toString() + " likes"
            holder.textView_porzioni.text = list!!.get(position).servings.toString() + " portions"
            holder.textView_tempo.text =
                (list!!.get(position).readyInMinutes.toString() + " minutes")
            Picasso.get().load(list!![position].image).into(holder.imageView_food)

            holder.favourite_list_container.setOnClickListener {
                listener.onClickRicetta(
                    list!![holder.adapterPosition].id.toString(),
                    list!![holder.adapterPosition].title.toString(),
                    list!![holder.adapterPosition].sourceName.toString(),
                    list!![holder.adapterPosition].readyInMinutes.toInt(),
                    list!![holder.adapterPosition].servings.toInt(),
                    list!![holder.adapterPosition].sourceUrl.toString(),
                    list!![holder.adapterPosition].image.toString(),
                    list!![holder.adapterPosition].imageType.toString(),
                    list!![holder.adapterPosition].instructions.toString(),
                    list!![holder.adapterPosition].spoonacularSourceUrl.toString()
                )
            }
            holder.show_btn.setOnClickListener {
                listener.onClickRicetta(
                    list!![holder.adapterPosition].id.toString(),
                    list!![holder.adapterPosition].title.toString(),
                    list!![holder.adapterPosition].sourceName.toString(),
                    list!![holder.adapterPosition].readyInMinutes.toInt(),
                    list!![holder.adapterPosition].servings.toInt(),
                    list!![holder.adapterPosition].sourceUrl.toString(),
                    list!![holder.adapterPosition].image.toString(),
                    list!![holder.adapterPosition].imageType.toString(),
                    list!![holder.adapterPosition].instructions.toString(),
                    list!![holder.adapterPosition].spoonacularSourceUrl.toString()
                )
            }
            holder.delete_btn.setOnClickListener{
                deleteListener.onClickDeleteRicetta(
                    list!![holder.adapterPosition].id.toString(),
                    list!![holder.adapterPosition].title.toString(),
                    list!![holder.adapterPosition].sourceName.toString(),
                    list!![holder.adapterPosition].readyInMinutes.toInt(),
                    list!![holder.adapterPosition].servings.toInt(),
                    list!![holder.adapterPosition].sourceUrl.toString(),
                    list!![holder.adapterPosition].image.toString(),
                    list!![holder.adapterPosition].imageType.toString(),
                    list!![holder.adapterPosition].instructions.toString(),
                    list!![holder.adapterPosition].spoonacularSourceUrl.toString()
                )
            }
        }


        override fun getItemCount(): Int {
            return list?.size ?: 0
        }
    }

    class RicettePreferiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favourite_list_container: CardView
        var textView_title: TextView
        var textView_porzioni: TextView
        var textView_likes: TextView
        var textView_tempo: TextView
        var imageView_food: ImageView
        var show_btn: Button
        var delete_btn: Button

        init {
            favourite_list_container = itemView.findViewById(R.id.favourite_list_container)
            textView_title = itemView.findViewById(R.id.textView_title)
            textView_porzioni = itemView.findViewById(R.id.textView_porzioni)
            textView_likes = itemView.findViewById(R.id.textView_likes)
            textView_tempo = itemView.findViewById(R.id.textView_tempo)
            imageView_food = itemView.findViewById(R.id.imageView_food)
            show_btn = itemView.findViewById(R.id.show_btn)
            delete_btn = itemView.findViewById(R.id.delete_btn)
        }
    }
