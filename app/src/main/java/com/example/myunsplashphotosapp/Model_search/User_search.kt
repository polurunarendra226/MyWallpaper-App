package com.example.myunsplashphotosapp.Model_search

data class User_search(
    val results: List<Result>,
    val total: Int?,
    val total_pages: Int?
)