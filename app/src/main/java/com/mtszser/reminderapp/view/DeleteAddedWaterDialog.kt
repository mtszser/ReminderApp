package com.mtszser.reminderapp.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.DrankWaterView
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeleteAddedWaterDialog(val drankWaterView: DrankWaterView): DialogFragment() {


    private val waterViewModel: WaterViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.dialog_water_delete_item, null))
                .setPositiveButton(R.string.delete
                ) { dialog, id ->
                    lifecycleScope.launch{
                        waterViewModel.deleteWaterAmount(drankWaterView)
                        dismiss()
                    }
                }
                .setNegativeButton(R.string.cancel,
                ) { dialog, id ->

                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

//    private lateinit var binding: DialogWaterDeleteItemBinding
//    private lateinit var deleteButton: AppCompatButton
//    private lateinit var cancelButton: AppCompatButton
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DialogWaterDeleteItemBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        deleteButton = binding.deleteWaterButton
//        cancelButton = binding.cancelDeleteButton
//
//        deleteButton.setOnClickListener {
//            dismiss()
//        }
//
//        cancelButton.setOnClickListener {
//            dismiss()
//        }
//    }

}