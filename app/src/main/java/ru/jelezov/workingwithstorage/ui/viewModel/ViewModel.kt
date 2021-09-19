package ru.jelezov.workingwithstorage.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import ru.jelezov.workingwithstorage.model.PersonModel
import ru.jelezov.workingwithstorage.repository.Repository
import ru.jelezov.workingwithstorage.utils.SharedPreferencesUtils
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var personList: LiveData<List<PersonModel>> = MutableLiveData()

    fun loadPerson(sortId: String?, baseId: Boolean) {
        viewModelScope.launch {
            if (sortId == "MAN") {
                personList = repository.readAllMan(baseId)
            } else
            if (sortId == "WOMAN") {
                personList = repository.readAllWoman(baseId)
            }
            else personList = repository.readAllPersons(baseId)
        }
    }

    fun addUser(person: PersonModel, baseId: Boolean) =
        viewModelScope.launch { repository.addUser(person, baseId) }


    fun deletePerson(person: PersonModel, baseId: Boolean) =
        viewModelScope.launch(Dispatchers.IO) { repository.deletePerson(person, baseId) }

    fun updatePerson(person: PersonModel, baseId: Boolean) =
        viewModelScope.launch { repository.updatePerson(person, baseId) }


}