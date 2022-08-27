package com.vytivskyi.gallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "urls") val urls: String,
    @ColumnInfo(name = "user") val user: String,
)
