package ru.jelezov.workingwithstorage.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import javax.inject.Inject

class SharedPreferencesUtils @Inject constructor(context: Context) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private fun getSortByAll(): String? {
        return sharedPreferences.getString("pref_sort_by", "All")
    }

    fun isSortByMan(): Boolean {
        return getSortByAll() == "MAN"
    }

    fun isSortByWoman(): Boolean {
        return getSortByAll() == "WOMAN"
    }

    fun isSortByAll(): Boolean {
        return getSortByAll() == "ALL"
    }

    fun getBaseId(): Boolean {
        return sharedPreferences.getBoolean("switch", false)
    }

}
