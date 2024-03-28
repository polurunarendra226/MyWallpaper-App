package com.example.myunsplashphotosapp.models_catogory

import java.io.Serializable

data class user_categoryItem(
    val cover_photo: CoverPhoto?,
    val current_user_contributions: List<Any>?,
    val description: String?,
    val ends_at: String?,
    val featured: Boolean?,
    val id: String?,
    val links: LinksXX?,
    val only_submissions_after: String?,
    val owners: List<Owner>?,
    val preview_photos: List<PreviewPhoto>?,
    val published_at: String?,
    val slug: String?,
    val starts_at: String?,
    val status: String?,
    val title: String,
    val total_current_user_submissions: Any?,
    val total_photos: Int?,
    val updated_at: String?,
    val visibility: String?
):Serializable