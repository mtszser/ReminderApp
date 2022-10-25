package com.mtszser.reminderapp.view

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.WaterRvItemBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.WaterContainers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ContRVAdapter @Inject constructor(
    private var drankWaterCards: List<DrankWaterBase>,
//    private val onCardClick: (DrankWaterBase) -> Unit,

) : RecyclerView.Adapter<ContRVAdapter.ViewWaterHolder>() {



    inner class ViewWaterHolder(view: View): RecyclerView.ViewHolder(view) {

        private var binding = WaterRvItemBinding.bind(view)

//        fun bindData(drankWaterItem: DrankWaterBase) {
//            with(itemView) {
//                val drankWaterImg = binding.waterContImg
//                val drankWaterTime = binding.waterContTime
//                val drankWaterCap = binding.waterContCap
//            }
//        }
        val drankWaterImg = binding.waterContImg
        val drankWaterCap = binding.waterContCap
        val drankWaterTime = binding.waterContTime


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWaterHolder =  ViewWaterHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.water_rv_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewWaterHolder, position: Int) {
        val drankWaterItem = drankWaterCards[position]
        holder.drankWaterImg.setImageResource(drankWaterItem.waterContImg)
        holder.drankWaterCap.text = drankWaterItem.waterContCap.toString() + "ml"

        fun getDate(): String {
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("hh:mm a")
            return formatter.format(time)
        }

        holder.drankWaterTime.text = getDate()
    }

    override fun getItemCount() = drankWaterCards.size
}

