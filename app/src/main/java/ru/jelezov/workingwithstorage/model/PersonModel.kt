package ru.jelezov.workingwithstorage.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "person_table")
data class PersonModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val gender: String
) : Parcelable