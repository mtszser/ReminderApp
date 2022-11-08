package com.mtszser.reminderapp.view

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.WaterRvItemBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.DrankWaterView
import com.mtszser.reminderapp.model.WaterContainers
import com.mtszser.reminderapp.model.mapToView
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ContRVAdapter(private val onItemClicked: (DrankWaterView) -> Unit)
    : ListAdapter<DrankWaterView, ContRVAdapter.ViewWaterHolder>(ListDiffCallBack) {


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
        holder.itemView.setOnClickListener{
            onItemClicked(drankWaterItem)
        }
        holder.drankWaterCap.text = drankWaterItem.dwCap.toString()
        holder.drankWaterImg.setImageResource(drankWaterItem.dwImg)
        holder.drankWaterTime.text = drankWaterItem.dwDate


    }
}

    object ListDiffCallBack: DiffUtil.ItemCallback<DrankWaterView>() {
        override fun areItemsTheSame(oldItem: DrankWaterView, newItem: DrankWaterView): Boolean
        = oldItem.dwID == newItem.dwID

        override fun areContentsTheSame(oldItem: DrankWaterView, newItem: DrankWaterView): Boolean
        = oldItem == newItem
    }

