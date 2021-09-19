package ru.jelezov.workingwithstorage.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.jelezov.workingwithstorage.data.room.RoomDataSource
import ru.jelezov.workingwithstorage.model.PersonModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val db: RoomDataSource
) {

    fun readAllPersons(baseId: Boolean): LiveData<List<PersonModel>> {
        Log.e("TAG", "BaseId -> $baseId")
       return when(baseId){
            false -> db.readAllPersons()
            true -> db.readAllPersonsSQL()
        }
    }

    suspend fun addUser(person: PersonModel, baseId: Boolean) {
        return when(baseId){
            false -> db.addUser(person)
            true -> db.addUserSQL(person)
        }
    }

    fun readAllMan(baseId: Boolean): LiveData<List<PersonModel>> {
        return when(baseId){
            false -> db.readAllMan()
            true -> db.readAllManSQL()
        }
    }

    fun readAllWoman(baseId: Boolean): LiveData<List<PersonModel>> {
        return when(baseId){
            false -> db.readAllWoman()
            true -> db.readAllWomanSQL()
        }
    }

    suspend fun deletePerson(person: PersonModel, baseId: Boolean) {
        return when(baseId){
            false -> db.deletePerson(person)
            true -> db.deletePersonSQL(person)
        }
    }

    suspend fun updatePerson(person: PersonModel, baseId: Boolean) {
        return when(baseId){
            false -> db.updatePerson(person)
            true ->  db.updatePersonSQL(person)
        }
    }

}