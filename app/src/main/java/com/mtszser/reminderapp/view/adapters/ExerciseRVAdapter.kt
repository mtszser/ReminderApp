package com.mtszser.reminderapp.view.adapters


import android.icu.text.MessageFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.ActivityRvItemBinding
import com.mtszser.reminderapp.model.DrankWaterView
import com.mtszser.reminderapp.model.ExerciseBaseView

class ExerciseRVAdapter(private val onExerciseClicked: (ExerciseBaseView) -> Unit)
    : ListAdapter<ExerciseBaseView, ExerciseRVAdapter.ViewExerciseHolder>(ExerciseDiffCallBack) {

    inner class ViewExerciseHolder(view: View): RecyclerView.ViewHolder(view) {

        private var binding = ActivityRvItemBinding.bind(view)

        val exerciseImg = binding.addedActivityImage
        val exerciseName = binding.addedActivityName
        val exerciseBonusWater = binding.addedActivityBonusWaterIntake
        val exerciseDeleteButton = binding.addedActivityDeleteButton

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewExerciseHolder =
        ViewExerciseHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_rv_item, parent, false)
        )



    override fun onBindViewHolder(holder: ViewExerciseHolder, position: Int) {

        val exerciseItem = getItem(position)
        holder.exerciseImg.setImageResource(exerciseItem.ebvImage)
        holder.exerciseBonusWater.text = "${exerciseItem.ebvBonusActivityWater} ml"
        holder.exerciseName.text = exerciseItem.ebvName
        holder.exerciseDeleteButton.setOnClickListener {
            onExerciseClicked(exerciseItem)
        }

    }
}

object ExerciseDiffCallBack: DiffUtil.ItemCallback<ExerciseBaseView>() {
    override fun areItemsTheSame(oldItem: ExerciseBaseView, newItem: ExerciseBaseView): Boolean
            = oldItem.ebvID == newItem.ebvID

    override fun areContentsTheSame(oldItem: ExerciseBaseView, newItem: ExerciseBaseView): Boolean
            = oldItem == newItem
}