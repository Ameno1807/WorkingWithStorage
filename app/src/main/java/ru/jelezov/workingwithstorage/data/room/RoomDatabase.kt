package ru.jelezov.workingwithstorage.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.jelezov.workingwithstorage.model.PersonModel


@Database(
    entities = [PersonModel::class],
    version = 1,
    exportSchema = false)

abstract class RoomDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDaoRoom
}