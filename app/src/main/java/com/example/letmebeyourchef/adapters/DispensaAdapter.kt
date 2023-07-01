package com.example.letmebeyourchef.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.letmebeyourchef.databinding.ItemIngredienteBinding
import androidx.recyclerview.widget.RecyclerView

class DispensaAdapter: RecyclerView.Adapter<DispensaAdapter.DispensaViewHolder>() {

    private var ingredientiList: List<String> = listOf()

    fun submitList(ingredienti: List<String>) {
        ingredientiList = ingredienti
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DispensaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredienteBinding.inflate(inflater, parent, false)
        return DispensaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DispensaViewHolder, position: Int) {
        val ingrediente = ingredientiList[position]
        holder.bind(ingrediente)
    }

    override fun getItemCount(): Int {
        return ingredientiList.size
    }

    inner class DispensaViewHolder(private val binding: ItemIngredienteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingrediente: String) {
            binding.nomeIngrediente.text = ingrediente
        }
    }
}