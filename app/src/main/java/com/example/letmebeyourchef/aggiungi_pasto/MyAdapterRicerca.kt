package com.example.letmebeyourchef.aggiungi_pasto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.model.Json_Parsing.Prodotto
import com.google.android.material.imageview.ShapeableImageView

//ArrayList<Prodotto>
class MyAdapterRicerca(private val productList : ArrayList<Prodotto>): RecyclerView.Adapter<MyAdapterRicerca.MyViewHolder>() {

    private  lateinit var mListener: onItemClickListener //Interfaccia che serve per associare un clickListener agli elementi della recycler view
    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.layout_item_prodotto,parent,false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentItem = productList[position]
            Glide.with(holder.itemView)
                .load(currentItem.image)
                .placeholder(R.drawable.no_image)
                .into(holder.productImage)
            holder.tvNomeProdotto.text = currentItem.label
            holder.tvCategoria.text = currentItem.category
            holder.tvEtichetta.text = currentItem.categoryLabel


    }

    override fun getItemCount(): Int {
        return productList.size

    }



    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val productImage : ShapeableImageView = itemView.findViewById(R.id.imageProdotto)
        val tvNomeProdotto : TextView = itemView.findViewById(R.id.tvNomeEsercizio)
        val tvCategoria : TextView = itemView.findViewById(R.id.tvCategoria)
        val tvEtichetta : TextView = itemView.findViewById(R.id.tvEtichetta)


        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }
}