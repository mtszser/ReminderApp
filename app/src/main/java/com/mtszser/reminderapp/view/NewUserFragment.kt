package com.mtszser.reminderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentNewUserBinding
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.model.WaterData
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewUserFragment : Fragment() {


    private lateinit var binding: FragmentNewUserBinding
    private var ID = (1..100).shuffled().first()
    private lateinit var newUserModel: NewUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewUserBinding.inflate(inflater, container, false)


        startUser()
        return binding.root
    }


    private fun startUser() {
        newUserModel = ViewModelProvider(this)[NewUserViewModel::class.java]
        val isProfile = newUserModel.getAll()

        isProfile.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                startApp()

            } else {
                Toast.makeText(context, "Hey stranger! Tell me about yourself.", Toast.LENGTH_SHORT).show()
                binding.saveUser.setOnClickListener{
                    saveData()
                }
            }

        })


    }

    private fun startApp() {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout, WaterFragment())
        fragmentTransaction?.commit()
    }

    private fun getInfo() {

       val dusza = newUserModel.getAll()
        dusza.observe(viewLifecycleOwner, Observer {
            it -> it?.let { binding.textView.text = it.toString() }
        })



    }


    private fun saveData() {
        val name = binding.name.text.toString()
        val weight = binding.weight.text.toString()
        val height = binding.height.text.toString()
        val profile = UserProfile(ID, name, weight, height)
        Log.i("userProfle", profile.toString())

        newUserModel.insert(profile)

    }





}