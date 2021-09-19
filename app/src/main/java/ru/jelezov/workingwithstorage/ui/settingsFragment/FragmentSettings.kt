package ru.jelezov.workingwithstorage.ui.settingsFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.preference.PreferenceFragmentCompat
import ru.jelezov.workingwithstorage.R

class FragmentSettings: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

}