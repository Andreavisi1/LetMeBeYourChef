package com.example.letmebeyourchef.adapters

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.databinding.StepItemLayoutBinding
import com.example.letmebeyourchef.model.directionModels.DirectionStepModel

class DirectionStepAdapter : RecyclerView.Adapter<DirectionStepAdapter.ViewHolder>() {
    private var directionStepModels: List<DirectionStepModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StepItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (directionStepModels != null) {
            val (distance, duration, _, htmlInstructions) = directionStepModels!![position]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.txtStepHtml.text =
                    Html.fromHtml(htmlInstructions, Html.FROM_HTML_MODE_LEGACY)
            } else {
                holder.binding.txtStepHtml.text = Html.fromHtml(htmlInstructions)
            }
            holder.binding.txtStepTime.text = duration!!.text
            holder.binding.txtStepDistance.text = distance!!.text
        }
    }

    override fun getItemCount(): Int {
        return if (directionStepModels != null) directionStepModels!!.size else 0
    }

    fun setDirectionStepModels(directionStepModels: List<DirectionStepModel>?) {
        this.directionStepModels = directionStepModels
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: StepItemLayoutBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}