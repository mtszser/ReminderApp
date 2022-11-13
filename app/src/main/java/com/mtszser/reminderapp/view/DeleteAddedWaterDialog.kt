package com.mtszser.reminderapp.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.DialogWaterDeleteItemBinding

class DeleteAddedWaterDialog(): DialogFragment() {

    private lateinit var binding: DialogWaterDeleteItemBinding
    private lateinit var deleteButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogWaterDeleteItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteButton = binding.deleteWaterButton
        cancelButton = binding.cancelDeleteButton

        deleteButton.setOnClickListener {
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

}