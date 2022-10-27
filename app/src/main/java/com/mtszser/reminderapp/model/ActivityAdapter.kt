package com.mtszser.reminderapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R

class ActivityAdapter: RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    private var activityList = mutableListOf<WaterContainers>()

    class ActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val contImg: ImageView = itemView.findViewById(R.id.waterContImg)
        val contCap: TextView = itemView.findViewById(R.id.containerCap)
        val onTime: TextView = itemView.findViewById(R.id.waterContTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.water_rv_item, parent,false)

        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activityList[position]
        holder.contCap.text = activity.containerCapacity
        holder.contImg.setImageResource(R.drawable.ic_glass_of_water_200ml)
        holder.onTime.text = ""
    }

    override fun getItemCount() = activityList.size

    fun setData(data: List<WaterContainers>) {
        activityList.apply {
            clear()
            addAll(data)
        }
    }


}