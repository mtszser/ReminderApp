package com.mtszser.reminderapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.WaterRvItemBinding
import com.mtszser.reminderapp.model.WaterContainers

class ContRVAdapter(
    private var drankWaterCards: List<WaterContainers>,
    private val onCardClick: (WaterContainers) -> Unit,

) : RecyclerView.Adapter<ContRVAdapter.ViewWaterHolder>() {



    inner class ViewWaterHolder(view: View): RecyclerView.ViewHolder(view) {

        private var binding = WaterRvItemBinding.bind(view)

        fun bindData(drankWaterItem: WaterContainers) {
            with(itemView) {
                val drankWaterImg = binding.waterContImg
                val drankWaterTime = binding.waterContTime
                val drankWaterCap = binding.waterContCap
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWaterHolder =  ViewWaterHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.water_rv_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewWaterHolder, position: Int) {
        val drankWaterItem = drankWaterCards[position]
        holder.bindData(drankWaterItem)
    }

    override fun getItemCount() = drankWaterCards.size
}

