package com.example.letmebeyourchef.diete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.model.Dieta

//ArrayList<Diete>
class MyAdapterDiete(private val dietaList : ArrayList<Dieta>, private val indiceDiete: Int): RecyclerView.Adapter<MyAdapterDiete.MyViewHolder>() {

    private  lateinit var mListener: onItemClickListener //Interfaccia che serve per associare un clickListener agli elementi della recycler view
    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.dieta_layout,parent,false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dietaList[position]
        Glide.with(holder.itemView)
            .load(currentItem.image)
            .placeholder(R.drawable.no_image)
            .into(holder.productImage)
        holder.titolo_dieta.text = currentItem.titolo
        if(indiceDiete == position)
            holder.layout_dieta.setBackgroundResource(R.drawable.animazione_card_selezionata)

    }

    override fun getItemCount(): Int {
        return dietaList.size

    }



    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val productImage : ImageView = itemView.findViewById(R.id.immagine)
        val titolo_dieta : TextView = itemView.findViewById(R.id.titolo_dieta)
        val layout_dieta : LinearLayout = itemView.findViewById(R.id.button)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }
}