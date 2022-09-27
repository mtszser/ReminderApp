package com.mtszser.reminderapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentActionBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActionFragment : Fragment() {

    private lateinit var binding: FragmentActionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_action, container, false)
    }

}