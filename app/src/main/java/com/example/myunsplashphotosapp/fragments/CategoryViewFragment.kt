package com.example.myunsplashphotosapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.adapters.wallpaperTopicAdapter
import com.example.myunsplashphotosapp.adapters.wallpapercategoryAdapter
import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryViewFragment : Fragment(R.layout.fragment_category_view) {

    val args:CategoryViewFragmentArgs by navArgs()
    val viewmodel: wallpaperviewmodel by viewModels()
    private lateinit var adapter: wallpapercategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var photos = args.userCategoryItem


       adapter= wallpapercategoryAdapter()

        view.findViewById<RecyclerView>(R.id.rv_category_view).adapter=adapter
        view.findViewById<RecyclerView>(R.id.rv_category_view).layoutManager=GridLayoutManager(activity,3)
        lifecycleScope.launch {
            viewmodel.listofTopicImages(photos).collectLatest{
                adapter.submitData(it)
                adapter.notifyDataSetChanged()
            }
        }

        adapter.setOnItemClickListner {
            findNavController().navigate(CategoryViewFragmentDirections.actionCategoryViewFragmentToDownload(it.urls?.raw.toString()))
        }





    }

}