package com.example.myunsplashphotosapp.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myunsplashphotosapp.MainActivity
import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.adapters.wallpaperAdapter
import com.example.myunsplashphotosapp.adapters.wallpaperTopicAdapter
import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategorykFragment : Fragment(R.layout.fragment_categoryk) {
    private val viewmodel: wallpaperviewmodel by viewModels()
    private lateinit var adapter: wallpaperTopicAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = wallpaperTopicAdapter()

        view.findViewById<RecyclerView>(R.id.rv_category).adapter=adapter
        view.findViewById<RecyclerView>(R.id.rv_category).layoutManager = GridLayoutManager(activity,2)

     viewmodel.listOfTopics.observe(viewLifecycleOwner, Observer {
         adapter.submitData(lifecycle,it)
         adapter.notifyDataSetChanged()
     })

        adapter.setOnItemClickListner {
         findNavController().navigate(CategorykFragmentDirections.actionCategorykFragmentToCategoryViewFragment(it.title))
        }

    }




}