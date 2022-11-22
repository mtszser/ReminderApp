package com.mtszser.reminderapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.BaseActivitiesSpinnerLayoutBinding
import com.mtszser.reminderapp.model.BaseActivities
import com.mtszser.reminderapp.util.Const

class ActivitySpinnerAdapter(context: Context): ArrayAdapter<BaseActivities>(context,0, Const.insertActivitySpinnerData()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        return this.createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {

        val baseActivityItem = getItem(position)

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.base_activities_spinner_layout, parent, false)
        val basicActivityName = view.findViewById<TextView>(R.id.basicActivityName)

        basicActivityName.text = baseActivityItem?.baseActivityName

        return view
    }
}
