package com.example.letmebeyourchef.aggiungi_pasto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.model.Pasto
import com.google.android.material.imageview.ShapeableImageView

class MyAdapterPrefPers(private val preferitiList : ArrayList<Pasto>): RecyclerView.Adapter<MyAdapterPrefPers.MyViewHolder>() {

    private  lateinit var mListener: onItemClickListener //Interfaccia che serve per associare un clickListener agli elementi della recycler view
    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_prodotto_pref_pers,parent,false)

        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = preferitiList[position]
        Glide.with(holder.itemView)
                .load(currentItem.image)
                .placeholder(R.drawable.no_image)
                .into(holder.immagine)

        holder.tvNomeProdotto.text = currentItem.nome
        holder.tvCalorie.text = currentItem.calorie.toString()
        holder.tvCarboidrati.text = currentItem.carboidrati.toString()
        holder.tvProteine.text = currentItem.proteine.toString()
        holder.tvGrassi.text = currentItem.grassi.toString()


    }

    override fun getItemCount(): Int {
        return preferitiList.size

    }



    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val immagine : ShapeableImageView = itemView.findViewById(R.id.immagine)
        val tvNomeProdotto : TextView = itemView.findViewById(R.id.tvNomeProdotto)
        val tvCalorie : TextView = itemView.findViewById(R.id.calorie)
        val tvCarboidrati : TextView = itemView.findViewById(R.id.tvCarboPers)
        val tvProteine : TextView = itemView.findViewById(R.id.tvProtPers)
        val tvGrassi : TextView = itemView.findViewById(R.id.tvGrassiPers)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }
}