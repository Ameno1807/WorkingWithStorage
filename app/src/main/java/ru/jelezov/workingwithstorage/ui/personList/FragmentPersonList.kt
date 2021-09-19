package ru.jelezov.workingwithstorage.ui.personList

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.jelezov.workingwithstorage.R
import ru.jelezov.workingwithstorage.databinding.PersonListBinding
import ru.jelezov.workingwithstorage.model.PersonModel
import ru.jelezov.workingwithstorage.ui.AdapterFragmentPersonList
import ru.jelezov.workingwithstorage.utils.PersonListener
import ru.jelezov.workingwithstorage.ui.viewModel.ViewModel

@AndroidEntryPoint
class FragmentPersonList: Fragment(), PersonListener {

    private var _binding: PersonListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadSettings()
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            val adapter = AdapterFragmentPersonList(this@FragmentPersonList)
            viewModel.personList.observe(viewLifecycleOwner, { person ->
                adapter.submitList(person)
            })
            this.adapter = adapter
        }

        binding.addPerson.setOnClickListener {
            view.findNavController().navigate(R.id.action_fragmentPersonList_to_fragmentAddPerson)
        }
    }

    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val baseId = sp.getBoolean("switch", false)
        if (baseId == false) {
            binding.nameDb.text = "Room Database"
        } else binding.nameDb.text = "SQLite Database"
        val sortId = sp.getString("pref_sort_by", "ALL")
        Log.e("Main", "$sortId")
        viewModel.loadPerson(sortId, baseId)
    }


    override fun updateUser(person: PersonModel) {
        val action = FragmentPersonListDirections.actionFragmentPersonListToFragmentUpdatePerson(person)
        view?.findNavController()?.navigate(action)
    }


}