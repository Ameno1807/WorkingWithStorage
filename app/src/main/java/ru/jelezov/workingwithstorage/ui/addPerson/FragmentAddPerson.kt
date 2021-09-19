package ru.jelezov.workingwithstorage.ui.addPerson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import ru.jelezov.workingwithstorage.R
import ru.jelezov.workingwithstorage.databinding.AddPersonBinding
import ru.jelezov.workingwithstorage.model.PersonModel
import ru.jelezov.workingwithstorage.ui.viewModel.ViewModel

@AndroidEntryPoint
class FragmentAddPerson: Fragment() {

    private var _binding: AddPersonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addPerson.setOnClickListener {
            view.findNavController().navigate(R.id.action_fragmentAddPerson_to_fragmentPersonList)
            addPerson()
        }
    }

    private fun addPerson() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val baseId = sp.getBoolean("switch", false)
        val spinner = binding.addAge
        val selected = spinner.selectedItem.toString()
        viewModel.addUser(
            PersonModel(
                0,
                binding.addName.text.toString(),
                binding.addSurname.text.toString(),
                selected
            ),
            baseId
        )
    }

}