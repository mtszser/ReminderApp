package com.mtszser.reminderapp.view

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.ActivityMainBinding
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var waterModel: WaterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        waterModel = ViewModelProvider(this)[WaterViewModel::class.java]
        replaceFragment(NewUserFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.actionReminder -> replaceFragment(ActionFragment())
                R.id.options -> replaceFragment(SettingsFragment())
                R.id.waterReminder -> replaceFragment((WaterFragment()))

                else -> {
                    Toast.makeText(applicationContext, "What are you trying to do?",
                        Toast.LENGTH_SHORT).show()}

            }
            true

        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}