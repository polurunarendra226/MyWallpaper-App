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
import com.example.myunsplashphotosapp.adapters.wallpaperCollectionAdapter
import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PapularFragment : Fragment(R.layout.fragment_papular) {
    private val viewmodel: wallpaperviewmodel by viewModels()
    private lateinit var adapter: wallpaperCollectionAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = wallpaperCollectionAdapter()

        view.findViewById<RecyclerView>(R.id.rv_popular).adapter=adapter
        view.findViewById<RecyclerView>(R.id.rv_popular).layoutManager = GridLayoutManager(activity,3)

        viewmodel.CollectionImages.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle,it)
            adapter.notifyDataSetChanged()
        })
        adapter.setOnItemClickListner {
            findNavController().navigate(PapularFragmentDirections.actionPapularFragmentToDownload(it.preview_photos[0].urls.raw))
        }

    }
}