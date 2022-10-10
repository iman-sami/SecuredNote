package com.imanp.securenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imanp.securenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home-> navController.navigate(R.id.homeFragment)
                R.id.fav->navController.navigate(R.id.likedFragment)
                R.id.setting->navController.navigate(R.id.settingFragment)
            }
            true
        }

    }
}