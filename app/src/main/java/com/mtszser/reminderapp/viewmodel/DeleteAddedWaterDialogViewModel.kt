package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.ViewModel
import com.mtszser.reminderapp.model.DrankWaterView
import com.mtszser.reminderapp.repository.DrankWaterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class DeleteAddedWaterDialogViewModel @Inject constructor(
    private val waterRepository: DrankWaterRepository,
    private val applicationScope: CoroutineScope): ViewModel() {

        fun onConfirmClick() = applicationScope.launch {
            waterRepository.getAddedWater()
        }

}