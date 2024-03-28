package com.example.myunsplashphotosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myunsplashphotosapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.searchbar)
        binding.searchbar.setupWithNavController(findNavController(R.id.fragmentContainerView))


        binding.bottomNavigationView.visibility=View.VISIBLE
        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.fragmentContainerView))

    }

//    override fun onSupportNavigateUp(): Boolean {
//        binding.bottomNavigationView.visibility=View.VISIBLE
//        binding.searchbar.visibility = View.VISIBLE
//        binding.root.visibility=View.VISIBLE
//        return findNavController(R.id.fragmentContainerView).navigateUp()|| super.onSupportNavigateUp()
//    }

    override fun onNavigateUp(): Boolean {
        binding.bottomNavigationView.visibility=View.VISIBLE
        binding.searchbar.visibility = View.VISIBLE
        binding.root.visibility=View.VISIBLE
        return findNavController(R.id.fragmentContainerView).navigateUp()||super.onNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search ->findNavController(R.id.fragmentContainerView).navigate(R.id.search)
        }
        return super.onOptionsItemSelected(item)
    }
}