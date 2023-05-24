package com.example.letmebeyourchef.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.Models.ResponseFromApiIstruzioni
import com.example.letmebeyourchef.R

class IstruzioniAdapter(var context: Context, var list: List<ResponseFromApiIstruzioni>) :
    RecyclerView.Adapter<IstruzioniViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IstruzioniViewHolder {
        return IstruzioniViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_istruzioni, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IstruzioniViewHolder, position: Int) {
        holder.textView_nome_istruzione.text = list[position].name
        holder.recycler_step_istruzione.setHasFixedSize(true)
        holder.recycler_step_istruzione.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val istruzioniStepAdapter = IstruzioniStepAdapter(context, list[position].steps)
        holder.recycler_step_istruzione.adapter = istruzioniStepAdapter
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class IstruzioniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_nome_istruzione: TextView
    var recycler_step_istruzione: RecyclerView

    init {
        textView_nome_istruzione = itemView.findViewById(R.id.textView_nome_istruzione)
        recycler_step_istruzione = itemView.findViewById(R.id.recycler_step_istruzione)
    }
}