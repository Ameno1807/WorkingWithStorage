package ru.jelezov.workingwithstorage.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.jelezov.workingwithstorage.model.PersonModel

@Dao
interface PersonDaoRoom {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(person: PersonModel)

    @Query("SELECT * FROM person_table ORDER BY id ASC")
    fun readAllPersons(): LiveData<List<PersonModel>>

    @Query("SELECT * FROM person_table WHERE gender like 'Man' ORDER BY id DESC")
    fun readAllMan():  LiveData<List<PersonModel>>

    @Query("SELECT * FROM person_table WHERE gender like 'Woman' ORDER BY id DESC")
    fun readAllWoman(): LiveData<List<PersonModel>>

    @Delete
    suspend fun deletePerson(person: PersonModel)

    @Update
    suspend fun updatePerson(person: PersonModel)
}