package com.example.myunsplashphotosapp.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.example.myunsplashphotosapp.Modelss.UsersItem
import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.models_catogory.user_categoryItem


class wallpaperAdapter:PagingDataAdapter<UsersItem,wallpaperAdapter.wallpaperviewholder>(differCallback) {
    lateinit var differ2: UsersItem


    inner class wallpaperviewholder(itemview:View):RecyclerView.ViewHolder(itemview)

    companion object{
        var differCallback = object :DiffUtil.ItemCallback<UsersItem>(){
            override fun areItemsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
               return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: wallpaperviewholder, position: Int) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wallpaperviewholder {
       return wallpaperviewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }
    private var onItemClickListner:((UsersItem)->Unit)?=null

    fun setOnItemClickListner(listner:(UsersItem)->Unit){
        onItemClickListner =listner
    }

}