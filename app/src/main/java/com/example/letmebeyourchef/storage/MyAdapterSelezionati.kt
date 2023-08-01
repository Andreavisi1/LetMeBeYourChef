package com.example.letmebeyourchef.storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.model.Pasto
import com.google.android.material.imageview.ShapeableImageView

//ArrayList<Diete>
class MyAdapterSelezionati(private val selezionatiList : ArrayList<Pasto>): RecyclerView.Adapter<MyAdapterSelezionati.MyViewHolder>() {

    private  lateinit var mListener: onItemClickListener //Interfaccia che serve per associare un clickListener agli elementi della recycler view
    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.layout_item_prodotto_selezionato,parent,false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = selezionatiList[position]
        Glide.with(holder.itemView)
            .load(currentItem.image)
            .placeholder(R.drawable.no_image)
            .into(holder.immagine)
        holder.tvNomeProdotto.text = currentItem.nome

    }

    override fun getItemCount(): Int {
        return selezionatiList.size

    }

    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val immagine : ShapeableImageView = itemView.findViewById(R.id.immagine)
        val tvNomeProdotto : TextView = itemView.findViewById(R.id.tvNomeEsercizio)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }
}