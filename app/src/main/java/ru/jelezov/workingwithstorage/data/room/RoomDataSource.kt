package ru.jelezov.workingwithstorage.data.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.jelezov.workingwithstorage.data.LocalDataSource
import ru.jelezov.workingwithstorage.data.cursor.PersonSQLiteOpenHelper
import ru.jelezov.workingwithstorage.data.cursor.PersonSQLiteOpenHelper.Companion.TABLE_NAME
import ru.jelezov.workingwithstorage.model.PersonModel
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val db: RoomDatabase,
    private val dbSQL: PersonSQLiteOpenHelper
): LocalDataSource {
    override suspend fun addUser(person: PersonModel) {
        return db.personDao().addUser(person)
    }

    override fun readAllMan(): LiveData<List<PersonModel>> {
      return db.personDao().readAllMan()
    }

    override fun readAllWoman(): LiveData<List<PersonModel>> {
        return db.personDao().readAllWoman()
    }

    override suspend fun deletePerson(person: PersonModel) {
        return db.personDao().deletePerson(person)
    }

    override suspend fun updatePerson(person: PersonModel) {
        return db.personDao().updatePerson(person)
    }

    override fun readAllPersonsSQL(): LiveData<List<PersonModel>> {
      return dbSQL.getItemList()
    }

    override suspend fun deleteAllPersonsSQL() {
        TODO("Not yet implemented")
    }

    override suspend fun addUserSQL(person: PersonModel) {
        dbSQL.addUser(person)
    }

    override fun readAllManSQL(): LiveData<List<PersonModel>> {
        return dbSQL.getItemListMan()
    }

    override fun readAllWomanSQL(): LiveData<List<PersonModel>> {
        return dbSQL.getItemListWoman()
    }

    override suspend fun deletePersonSQL(person: PersonModel) {
        return dbSQL.deleteSQL(person)
    }

    override suspend fun updatePersonSQL(person: PersonModel) {
        dbSQL.edit(person)
    }

    override fun readAllPersons(): LiveData<List<PersonModel>> {
        return db.personDao().readAllPersons()
    }



}