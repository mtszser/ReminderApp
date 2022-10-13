package com.mtszser.reminderapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.WaterContainers

class ContainerAdapter(context: Context, containers: List<WaterContainers>): ArrayAdapter<WaterContainers>(context, 0, containers) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {

        val container = getItem(position)

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_layout, parent, false)
        val contImg = view.findViewById<ImageView>(R.id.containerImg)
        val contCap = view.findViewById<TextView>(R.id.containerCap)

        if (container != null) {
            contImg.setImageResource(container.containerImg)
        }
        contCap.text = container?.containerCapacity + "ml"

        return view
    }

}