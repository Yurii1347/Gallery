package com.vytivskyi.gallery.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): PhotoDao
}