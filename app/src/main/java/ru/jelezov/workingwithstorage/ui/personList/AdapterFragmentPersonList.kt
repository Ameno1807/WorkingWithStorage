package ru.jelezov.workingwithstorage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.jelezov.workingwithstorage.databinding.ItemPersonBinding
import ru.jelezov.workingwithstorage.model.PersonModel
import ru.jelezov.workingwithstorage.utils.PersonListener


class AdapterFragmentPersonList(
    private val listener: PersonListener
): ListAdapter<PersonModel, AdapterFragmentPersonList.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class ViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PersonModel){
            binding.name.text = item.name
            binding.surname.text = item.surname
            binding.age.text = item.gender

            binding.editItem.setOnClickListener {
                listener.updateUser(item)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class DiffCallback : DiffUtil.ItemCallback<PersonModel>() {
    override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
        return oldItem.id == newItem.id
    }
}