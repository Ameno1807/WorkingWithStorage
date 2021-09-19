package ru.jelezov.workingwithstorage.data.cursor

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import ru.jelezov.workingwithstorage.model.PersonModel
import javax.inject.Inject

class PersonSQLiteOpenHelper@Inject constructor(context: Context?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    private suspend fun getAll():List<PersonModel>{
        return withContext(Dispatchers.IO) {
            val listOfPerson = mutableListOf<PersonModel>()
            val db = writableDatabase
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val cursor = db.rawQuery(selectQuery,null)
            cursor?.let{
                if (cursor.moveToFirst()) {
                    do {
                        val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                        val surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"))
                        val gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                        val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))

                        listOfPerson.add(PersonModel(id, name, surname, gender))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            listOfPerson
        }
    }

    private suspend fun getAllMan():List<PersonModel>{
        return withContext(Dispatchers.IO) {
            val listOfPerson = mutableListOf<PersonModel>()
            val db = writableDatabase
            val selectQuery = "SELECT * FROM $TABLE_NAME WHERE gender like 'Man'"
            val cursor = db.rawQuery(selectQuery,null)
            cursor?.let{
                if (cursor.moveToFirst()) {
                    do {
                        val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                        val surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"))
                        val gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                        val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))

                        listOfPerson.add(PersonModel(id, name, surname, gender))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            listOfPerson
        }
    }

    private suspend fun getAllWoman():List<PersonModel>{
        return withContext(Dispatchers.IO) {
            val listOfPerson = mutableListOf<PersonModel>()
            val db = writableDatabase
            val selectQuery = "SELECT * FROM $TABLE_NAME WHERE gender like 'Woman'"
            val cursor = db.rawQuery(selectQuery,null)
            cursor?.let{
                if (cursor.moveToFirst()) {
                    do {
                        val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                        val surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"))
                        val gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                        val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))

                        listOfPerson.add(PersonModel(id, name, surname, gender))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            listOfPerson
        }
    }

     fun getItemList(): LiveData<List<PersonModel>> {
         return liveData {
             emit(getAll())
         }
     }


    fun getItemListMan(): LiveData<List<PersonModel>> {
        return liveData {
            emit(getAllMan())
        }
    }

    fun getItemListWoman(): LiveData<List<PersonModel>> {
        return liveData {
            emit(getAllWoman())
        }
    }

    fun addUser(person: PersonModel){
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, person.name)
        values.put(KEY_SURNAME, person.surname)
        values.put(KEY_AGE, person.gender)
        db.insert("person_table", null, values)
        db.close()
    }

    fun edit(person: PersonModel) {
        val db = writableDatabase
         val values = ContentValues()
         values.put(KEY_ID, person.id)
         values.put(KEY_NAME, person.name)
         values.put(KEY_SURNAME, person.surname)
         values.put(KEY_AGE, person.gender)

        db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(person.id.toString()))
        db.close()
    }

    fun deleteSQL(person: PersonModel) {
        writableDatabase.delete(TABLE_NAME, "$KEY_ID=?", Array(1) { "${person.id}" })
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "myDb"
        const val TABLE_NAME = "person_table"
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_SURNAME = "surname"
        const val KEY_AGE = "gender"
        const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `person_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `surname` TEXT NOT NULL, `gender` TEXT NOT NULL)"
    }
}