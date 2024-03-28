package com.example.myunsplashphotosapp.fragments

import android.app.Activity
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.example.myunsplashphotosapp.R
import com.example.myunsplashphotosapp.vm.wallpaperviewmodel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class Download : Fragment(R.layout.fragment_download) {

    val args:DownloadArgs by navArgs()
    private lateinit var image:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         image = args.photo
        activity?.let { Glide.with(it).load(image.toUri()).into(view.findViewById<ImageView>(R.id.image)) }
        view.findViewById<Button>(R.id.download_button).setOnClickListener {
            downloadimage(image)
        }
        view.findViewById<Button>(R.id.wallpaper_button).setOnClickListener {
            setWalpaper()
        }

    }
    private fun downloadimage(url:String){
        try {
            var dowloadManager= activity?.getSystemService(Activity.DOWNLOAD_SERVICE) as DownloadManager

            var imageurl = Uri.parse(url)
            var request = DownloadManager.Request(imageurl).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setMimeType("image/*")
                    .setAllowedOverRoaming(false)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setTitle("Download....!")
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES,
                        File.separator +"download"+".jpg"
                    )
            }
            dowloadManager.enqueue(request)
            Toast.makeText(activity,"Downloading......!",Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(activity,"${e.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun setWalpaper(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            try {
                val wallpaperManager= WallpaperManager.getInstance(activity)
                var image = activity?.findViewById<ImageView>(R.id.image)
                if (image?.drawable ==null){
                    Toast.makeText(activity,"Wait to download",Toast.LENGTH_LONG).show()
                }else{
                    val bitmap = (image.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap,null,true)
                    Toast.makeText(activity,"Done",Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception){
                Toast.makeText(activity,"${e.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

}