package com.vytivskyi.gallery.model.repository

import androidx.lifecycle.LiveData
import com.vytivskyi.gallery.Constance
import com.vytivskyi.gallery.database.AppDatabase
import com.vytivskyi.gallery.database.PhotoEntity
import com.vytivskyi.gallery.model.ApiInterface
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase
) {

    fun observePhotos(): LiveData<List<PhotoEntity>> {
        return appDatabase.videoDao().observeAll()
    }

    suspend fun getPhotos() {
        runCatching { apiInterface.getPhotos(Constance.UNSPLASH_API_KEY) }
            .onSuccess { photoDto ->
                photoDto.body()?.let {
                    appDatabase.videoDao().putPhotos(
                        it.map {
                            PhotoEntity(
                                id = it.id,
                                description = it.description,
                                urls = it.urls.regular,
                                user = it.user.name
                            )
                        }
                    )
                }
            }
    }
}

