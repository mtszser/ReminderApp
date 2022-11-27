package com.mtszser.reminderapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.util.Const


class ContainerAdapter(context: Context): ArrayAdapter<DrankWaterBase>(context, 0, Const.insertSpinnerData()) {


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
            contImg.setImageResource(container.waterContImg)
        }
        contCap.text = container?.waterContCap.toString() + "ml"

        return view
    }

}