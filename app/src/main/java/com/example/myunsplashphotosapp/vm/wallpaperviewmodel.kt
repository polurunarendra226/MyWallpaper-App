package com.example.myunsplashphotosapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.myunsplashphotosapp.repositories.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class wallpaperviewmodel @Inject constructor(private val repository: WallpaperRepository):ViewModel() {

    var listOfImages=repository.getPhotos().cachedIn(viewModelScope)
    var CollectionImages=repository.getCollectionePhotos().cachedIn(viewModelScope)
    var RandomImages=repository.getRandomPhotos().cachedIn(viewModelScope)
    var listOfTopics=repository.getTopics().cachedIn(viewModelScope)
    fun listofTopicImages(searchtext:String)=repository.getcategoryphotos(searchtext)
    fun listofsearchImages2(searchtext:String)=repository.getsearchphotos2(searchtext)



}