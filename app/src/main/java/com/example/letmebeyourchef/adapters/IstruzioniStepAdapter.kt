package com.example.letmebeyourchef.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.recipeModels.Step
import com.example.letmebeyourchef.R

class IstruzioniStepAdapter constructor(var context: Context, var list: List<Step?>?) :
    RecyclerView.Adapter<IstruzioniStepViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IstruzioniStepViewHolder {
        return IstruzioniStepViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_step_istruzioni, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IstruzioniStepViewHolder, position: Int) {
        holder.textView_numero_step_istruzioni.text = list!!.get(position)!!.number.toString()
        holder.textView_titolo_step_istruzioni.text = list!!.get(position)!!.step
        holder.recycler_istruzioni_ingredienti.setHasFixedSize(true)
        holder.recycler_istruzioni_ingredienti.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val istruzioniIngredientiAdapter: IstruzioniIngredientiAdapter =
            IstruzioniIngredientiAdapter(
                context, list!!.get(position)!!.ingredients
            )
        holder.recycler_istruzioni_ingredienti.adapter = istruzioniIngredientiAdapter
        holder.recycler_istruzioni_strumenti.setHasFixedSize(true)
        holder.recycler_istruzioni_strumenti.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val istruzioniStrumentiAdapter: IstruzioniStrumentiAdapter = IstruzioniStrumentiAdapter(
            context, list!!.get(position)!!.equipment
        )
        holder.recycler_istruzioni_strumenti.adapter = istruzioniStrumentiAdapter
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}

class IstruzioniStepViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView_numero_step_istruzioni: TextView
    var textView_titolo_step_istruzioni: TextView
    var recycler_istruzioni_strumenti: RecyclerView
    var recycler_istruzioni_ingredienti: RecyclerView

    init {
        textView_numero_step_istruzioni =
            itemView.findViewById(R.id.textView_numero_step_istruzioni)
        textView_titolo_step_istruzioni =
            itemView.findViewById(R.id.textView_titolo_step_istruzioni)
        recycler_istruzioni_strumenti = itemView.findViewById(R.id.recycler_istruzioni_strumenti)
        recycler_istruzioni_ingredienti =
            itemView.findViewById(R.id.recycler_istruzioni_ingredienti)
    }
}