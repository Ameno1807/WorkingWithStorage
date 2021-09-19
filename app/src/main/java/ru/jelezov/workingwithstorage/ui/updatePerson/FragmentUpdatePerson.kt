package ru.jelezov.workingwithstorage.ui.updatePerson

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import ru.jelezov.workingwithstorage.R
import ru.jelezov.workingwithstorage.databinding.UpdatePersonBinding
import ru.jelezov.workingwithstorage.model.PersonModel
import ru.jelezov.workingwithstorage.ui.viewModel.ViewModel

@AndroidEntryPoint
class FragmentUpdatePerson: Fragment() {

    private var _binding: UpdatePersonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by viewModels()
    private val args by navArgs<FragmentUpdatePersonArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UpdatePersonBinding.inflate(inflater, container, false)

        binding.addName.setText(args.currentUser.name)
        binding.addSurname.setText(args.currentUser.surname)
        binding.addAge.setSelection(defaultSelected(args.currentUser.gender))

      binding.updatePerson.setOnClickListener {
          val sp = PreferenceManager.getDefaultSharedPreferences(context)
          val baseId = sp.getBoolean("switch", false)
          val spinner = binding.addAge
          val selected = spinner.selectedItem.toString()
            viewModel.updatePerson(
                PersonModel(
                    args.currentUser.id,
                    binding.addName.text.toString(),
                    binding.addSurname.text.toString(),
                    selected
                ),
                baseId
            )
          view?.findNavController()?.navigate(R.id.action_fragmentUpdatePerson_to_fragmentPersonList)
        }

        binding.deletePerson.setOnClickListener {
            deleteUser()
            view?.findNavController()?.navigate(R.id.action_fragmentUpdatePerson_to_fragmentPersonList)
        }

        return binding.root
    }

    private fun deleteUser() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val baseId = sp.getBoolean("switch", false)
        viewModel.deletePerson(args.currentUser, baseId)
    }

    private fun defaultSelected(gender: String): Int {
        return when(gender) {
            "Man" -> 0
            else -> 1
        }
    }
}