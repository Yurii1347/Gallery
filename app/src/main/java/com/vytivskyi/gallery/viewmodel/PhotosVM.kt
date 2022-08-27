package com.vytivskyi.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.vytivskyi.gallery.database.dto.PhotoDtoItem
import com.vytivskyi.gallery.database.dto.Urls
import com.vytivskyi.gallery.database.dto.User
import com.vytivskyi.gallery.model.repository.PhotoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosVM @Inject constructor(
    private val photoRepositoryImpl: PhotoRepositoryImpl
) : ViewModel() {

    fun observePhotos(): LiveData<List<PhotoDtoItem>> {
        return photoRepositoryImpl.observePhotos().map {
            it.map {
                PhotoDtoItem(
                    it.description?: "Have no title",
                    it.id,
                    Urls(it.urls),
                    User(it.user)
                )
            }
        }
    }

    fun getPhotos() {
        viewModelScope.launch {
            photoRepositoryImpl.getPhotos()
        }
    }
}