package com.example.myunsplashphotosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myunsplashphotosapp.Model_search.Result
import com.example.myunsplashphotosapp.Modelss.UsersItem
import com.example.myunsplashphotosapp.R


class wallpaperSearchAdapter:PagingDataAdapter<Result, wallpaperSearchAdapter.wallpapersearchviewholder>(differCallback) {
    lateinit var differ2: Result

    inner class wallpapersearchviewholder(itemview: View): RecyclerView.ViewHolder(itemview)
    companion object{
        var differCallback = object :DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: wallpapersearchviewholder, position: Int) {
        var currentitem=getItem(position)
        holder.itemView.apply {
            if (currentitem != null) {
                differ2=currentitem
                Glide.with(this).load(currentitem?.urls?.raw).into(findViewById(R.id.iv_name))
                setOnClickListener {
                    onItemClickListner?.let {
                        it(differ2)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wallpapersearchviewholder {
return wallpapersearchviewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }
    private var onItemClickListner:((Result)->Unit)?=null

    fun setOnItemClickListner(listner:(Result)->Unit){
        onItemClickListner =listner
    }

}