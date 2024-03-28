package com.example.myunsplashphotosapp.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.adapters.wallpaperAdapter
import com.example.myunsplashphotosapp.adapters.wallpaperRandomAdapter
import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFragment : Fragment(R.layout.fragment_random) {
    private val viewmodel: wallpaperviewmodel by viewModels()
    private lateinit var adapter: wallpaperRandomAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = wallpaperRandomAdapter()

        view.findViewById<RecyclerView>(R.id.rv_random).adapter=adapter
        view.findViewById<RecyclerView>(R.id.rv_random).layoutManager = GridLayoutManager(activity,3)

   viewmodel.RandomImages.observe(viewLifecycleOwner,{
       adapter.submitData(lifecycle,it)
       adapter.notifyDataSetChanged()
   })
        adapter.setOnItemClickListner {
            findNavController().navigate(RandomFragmentDirections.actionRandomFragmentToDownload(it.urls?.raw.toString()))
        }

    }
}