package com.example.myunsplashphotosapp.fragments


import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.adapters.wallpaperAdapter
import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
private val viewmodel:wallpaperviewmodel by viewModels()
    private lateinit var adapter:wallpaperAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = wallpaperAdapter()
         view.findViewById<RecyclerView>(R.id.rv_home).adapter=adapter
        view.findViewById<RecyclerView>(R.id.rv_home).layoutManager = GridLayoutManager(activity,3)
        viewmodel.listOfImages.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle,it)
            adapter.notifyDataSetChanged()
        })

        adapter.setOnItemClickListner {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDownload(it.urls.raw))
        }

    }

}