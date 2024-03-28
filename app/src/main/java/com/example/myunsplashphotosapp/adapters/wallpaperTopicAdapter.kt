package com.example.myunsplashphotosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.paging.PagingSource
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myunsplashphotosapp.Modelss.UsersItem
import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.models_catogory.user_categoryItem


class wallpaperTopicAdapter:PagingDataAdapter<user_categoryItem,wallpaperTopicAdapter.wallpaperviewholder>(differCallback) {
    lateinit var differ2:user_categoryItem


    inner class wallpaperviewholder(itemview:View):RecyclerView.ViewHolder(itemview)

    companion object{
        var differCallback = object :DiffUtil.ItemCallback<user_categoryItem>(){
            override fun areItemsTheSame(oldItem: user_categoryItem, newItem: user_categoryItem): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: user_categoryItem, newItem: user_categoryItem): Boolean {
               return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: wallpaperviewholder, position: Int) {
       var currentitem=getItem(position)
        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_name_c).text=currentitem?.title.toString()
            if (currentitem != null) {
                differ2=currentitem
                Glide.with(this).load(currentitem.preview_photos?.get(0)?.urls?.raw).into(findViewById(R.id.iv_name_c))

                setOnClickListener {
                    onItemClickListner?.let {
                        it(differ2)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wallpaperviewholder {
       return wallpaperviewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_2,parent,false))
    }

    private var onItemClickListner:((user_categoryItem)->Unit)?=null

    fun setOnItemClickListner(listner:(user_categoryItem)->Unit){
        onItemClickListner =listner
    }

}