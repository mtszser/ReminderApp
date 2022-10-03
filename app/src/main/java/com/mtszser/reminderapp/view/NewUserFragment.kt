package com.mtszser.reminderapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mtszser.reminderapp.databinding.FragmentNewUserBinding
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewUserFragment : Fragment() {


    private lateinit var binding: FragmentNewUserBinding
    private val userModel: NewUserViewModel by viewModels()

    private val name: String
        get() = binding.name.text.toString()

    private val weight: String
        get() = binding.weight.text.toString()

    private val height: String
        get() = binding.height.text.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewUserBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            userModel.allUsers.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    Toast.makeText(
                        context,
                        "Hey stranger! Tell me about yourself.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.saveUser.setOnClickListener {
                        userModel.startApp()
                        val userProfile = UserProfile(0, name, weight, height)
                        userModel.insert(userProfile)
//                        val intent = Intent(context, MainActivity::class.java)
//                        startActivity(intent)
                    }
                }
            })
        }

    }






