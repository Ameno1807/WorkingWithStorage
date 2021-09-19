package ru.jelezov.workingwithstorage.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.jelezov.workingwithstorage.model.PersonModel

interface LocalDataSource {
    fun readAllPersons(): LiveData<List<PersonModel>>
    suspend fun addUser(person: PersonModel)
    fun readAllMan(): LiveData<List<PersonModel>>
    fun readAllWoman(): LiveData<List<PersonModel>>
    suspend fun deletePerson(person: PersonModel)
    suspend fun updatePerson(person: PersonModel)

    fun readAllPersonsSQL(): LiveData<List<PersonModel>>
    suspend fun deleteAllPersonsSQL()
    suspend fun addUserSQL(person: PersonModel)
    fun readAllManSQL(): LiveData<List<PersonModel>>
    fun readAllWomanSQL(): LiveData<List<PersonModel>>
    suspend fun deletePersonSQL(person: PersonModel)
    suspend fun updatePersonSQL(person: PersonModel)
}