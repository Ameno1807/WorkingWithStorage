package ru.jelezov.workingwithstorage.utils

import ru.jelezov.workingwithstorage.model.PersonModel

interface PersonListener {
    fun updateUser(person: PersonModel)
}