package com.example.letmebeyourchef.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.Listeners.RicettaClickListener
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteSimili
import com.example.letmebeyourchef.R
import com.squareup.picasso.Picasso

class RicetteSimiliAdapter constructor(
    var context: Context,
    var list: List<ResponseFromApiRicetteSimili>,
    var listener: RicettaClickListener
) : RecyclerView.Adapter<RicetteSimiliViewHolder>() {
    public override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RicetteSimiliViewHolder {
        return RicetteSimiliViewHolder(
            LayoutInflater.from(context).inflate((R.layout.list_ricette_simili), parent, false)
        )
    }

    public override fun onBindViewHolder(holder: RicetteSimiliViewHolder, position: Int) {
        holder.textView_simili_titolo.setText(list.get(position).title)
        holder.textView_simili_titolo.setSelected(true)
        holder.textView_simili_porzione.setText(list.get(position).servings.toString() + " persone")
        Picasso.get().load(
            "https://spoonacular.com/recipeImages/" + list.get(position).id + "-556x370." + list.get(
                position
            ).imageType
        ).into(holder.imageView_simili)
        holder.ricette_simili_holder.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                listener.onClickRicetta(list.get(holder.getAdapterPosition()).id.toString())
            }
        })
    }

    public override fun getItemCount(): Int {
        return list.size
    }
}

class RicetteSimiliViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ricette_simili_holder: CardView
    var textView_simili_titolo: TextView
    var textView_simili_porzione: TextView
    var imageView_simili: ImageView

    init {
        ricette_simili_holder = itemView.findViewById(R.id.ricette_simili_holder)
        textView_simili_titolo = itemView.findViewById(R.id.textView_simili_titolo)
        textView_simili_porzione = itemView.findViewById(R.id.textView_simili_porzione)
        imageView_simili = itemView.findViewById(R.id.imageView_simili)
    }
}