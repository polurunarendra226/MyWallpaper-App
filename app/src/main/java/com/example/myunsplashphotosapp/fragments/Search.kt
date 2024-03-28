package com.example.myunsplashphotosapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.EditText

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.adapters.wallpaperSearchAdapter

import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Search : Fragment(R.layout.search) {
    private val viewmodel: wallpaperviewmodel by viewModels()
    private lateinit var adapter: wallpaperSearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= wallpaperSearchAdapter()

        var rvsearch=view.findViewById<RecyclerView>(R.id.rv_search)
        rvsearch.adapter=adapter
        rvsearch.layoutManager=GridLayoutManager(activity,3)

        view.findViewById<EditText>(R.id.search_item_text).addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    if (s != null) {
                        viewmodel.listofsearchImages2(s.trim().toString()).collectLatest {
                            adapter.submitData(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                lifecycleScope.launch {
                    if (s != null) {
                        viewmodel.listofsearchImages2(s.trim().toString()).collectLatest {
                            adapter.submitData(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }

            }


        })

        adapter.setOnItemClickListner {
            findNavController().navigate(SearchDirections.actionSearchToDownload(it.urls?.raw.toString()))
        }
    }

}