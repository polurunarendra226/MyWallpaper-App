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
import com.example.myunsplashphotosapp.models_Random.User_Random

class wallpaperRandomAdapter: PagingDataAdapter<User_Random, wallpaperRandomAdapter.wallpaperRandomviewholder>(differCallback) {
    lateinit var differ2: User_Random

    inner class wallpaperRandomviewholder(itemview: View): RecyclerView.ViewHolder(itemview)

    companion object{
        var differCallback = object :DiffUtil.ItemCallback<User_Random>(){
            override fun areItemsTheSame(oldItem: User_Random, newItem: User_Random): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: User_Random, newItem: User_Random): Boolean {
                return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: wallpaperRandomviewholder, position: Int) {
            var currentitem = getItem(position)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wallpaperRandomviewholder {
      return wallpaperRandomviewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }
    private var onItemClickListner:((User_Random)->Unit)?=null

    fun setOnItemClickListner(listner:(User_Random)->Unit){
        onItemClickListner =listner
    }
}