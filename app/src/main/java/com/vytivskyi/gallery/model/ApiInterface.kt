package com.vytivskyi.gallery.model


import com.vytivskyi.gallery.database.dto.PhotoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("photos/")
    suspend fun getPhotos(@Query("client_id") sort: String): Response<PhotoDto>
}