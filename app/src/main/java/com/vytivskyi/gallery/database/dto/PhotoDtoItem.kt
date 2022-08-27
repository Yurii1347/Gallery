package com.vytivskyi.gallery.database.dto

data class PhotoDtoItem(
    val description: String,
    val id: String,
    val urls: Urls,
    val user: User,
)