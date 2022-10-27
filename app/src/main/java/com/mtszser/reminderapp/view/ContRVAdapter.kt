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

) : RecyclerView.Adapter<ContRVAdapter.ViewWaterHolder>() {



    inner class ViewWaterHolder(view: View): RecyclerView.ViewHolder(view) {

        private var binding = WaterRvItemBinding.bind(view)


        val drankWaterImg = binding.waterContImg
        val drankWaterCap = binding.waterContCap
        val drankWaterTime = binding.waterContTime


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWaterHolder =  ViewWaterHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.water_rv_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewWaterHolder, position: Int) {
        val drankWaterItem = drankWaterCards[position]
        val mapDrankItem = drankWaterItem.mapDataClass()
        holder.drankWaterCap.text = mapDrankItem.getCap()
        holder.drankWaterImg.setImageResource(mapDrankItem.dwImg)
        holder.drankWaterTime.text = mapDrankItem.dwDate

    }

    override fun getItemCount() = drankWaterCards.size
}

