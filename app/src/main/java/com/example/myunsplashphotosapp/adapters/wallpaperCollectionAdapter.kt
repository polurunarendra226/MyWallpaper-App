package com.example.myunsplashphotosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myunsplashphotosapp.Modelss.UsersItem

import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.models.User5Item

class wallpaperCollectionAdapter:PagingDataAdapter<User5Item,wallpaperCollectionAdapter.wallpaperviewholder>(differCallback) {
    lateinit var differ2: User5Item

    inner class wallpaperviewholder(itemview:View):RecyclerView.ViewHolder(itemview)

    companion object{
        var differCallback = object :DiffUtil.ItemCallback<User5Item>(){
            override fun areItemsTheSame(oldItem: User5Item, newItem: User5Item): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: User5Item, newItem: User5Item): Boolean {
               return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: wallpaperviewholder, position: Int) {
       var currentitem=getItem(position)
        holder.itemView.apply {
            if (currentitem != null) {
                differ2=currentitem
                Glide.with(this).load(currentitem?.preview_photos?.get(0)?.urls?.raw).into(findViewById(R.id.iv_name_c))
                setOnClickListener {
                    onItemClickListner?.let {
                        it(differ2)
                    }
                }
            }

          //  findViewById<TextView>(R.id.iv_2_name).text=currentitem?.id

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wallpaperviewholder {
       return wallpaperviewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_2,parent,false))
    }
    private var onItemClickListner:((User5Item)->Unit)?=null

    fun setOnItemClickListner(listner:(User5Item)->Unit){
        onItemClickListner =listner
    }

}