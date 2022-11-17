package com.mtszser.reminderapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.ActivityRvItemBinding
import com.mtszser.reminderapp.databinding.WaterRvItemBinding
import com.mtszser.reminderapp.model.DrankWaterView

class ContRVAdapter(private val onItemClicked: (DrankWaterView) -> Unit)
    : ListAdapter<DrankWaterView, ContRVAdapter.ViewWaterHolder>(ListDiffCallBack){

    inner class ViewWaterHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var binding = WaterRvItemBinding.bind(view)


        val drankWaterImg = binding.waterContImg
        val drankWaterCap = binding.waterContCap
        val drankWaterTime = binding.waterContTime
        val drankWaterButton = binding.capButton


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWaterHolder =
        ViewWaterHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.water_rv_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewWaterHolder, position: Int) {
        val drankWaterItem = getItem(position)
        holder.drankWaterCap.text = drankWaterItem.dwCap.toString() + "ml"
        holder.drankWaterImg.setImageResource(drankWaterItem.dwImg)
        holder.drankWaterTime.text = drankWaterItem.dwDate
        holder.drankWaterButton.setOnClickListener {
            onItemClicked(drankWaterItem)
        }
    }

}

    object ListDiffCallBack: DiffUtil.ItemCallback<DrankWaterView>() {
        override fun areItemsTheSame(oldItem: DrankWaterView, newItem: DrankWaterView): Boolean
        = oldItem.dwID == newItem.dwID

        override fun areContentsTheSame(oldItem: DrankWaterView, newItem: DrankWaterView): Boolean
        = oldItem == newItem
    }

