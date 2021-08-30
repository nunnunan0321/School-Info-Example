package com.note11.schoolinfoapp.ui.screen.first.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.databinding.ItemSearchBinding

class SearchAdapter(private val onClick: ((SchoolModel) -> Unit)? = null) :
    ListAdapter<SchoolModel, SearchAdapter.SearchViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding: ItemSearchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search,
            parent,
            false
        )

        val viewHolder = SearchViewHolder(binding)

        onClick?.let { f ->
            binding.root.setOnClickListener {
                f(getItem(viewHolder.absoluteAdapterPosition))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SchoolModel) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class SearchDiffUtil : DiffUtil.ItemCallback<SchoolModel>() {
        override fun areItemsTheSame(oldItem: SchoolModel, newItem: SchoolModel): Boolean =
            oldItem.schoolCode == newItem.schoolCode

        override fun areContentsTheSame(oldItem: SchoolModel, newItem: SchoolModel): Boolean =
            oldItem == newItem
    }
}
