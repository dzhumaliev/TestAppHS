package com.io.testapphs.ui.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.io.domain.models.Country
import com.io.testapphs.R
import com.io.testapphs.databinding.ItemCountryBinding


class CountryAdapter :
    androidx.recyclerview.widget.ListAdapter<Country, CountryAdapter.CountryVH>(CountryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVH {
        return CountryVH(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class CountryDiffCallBack : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    var onItemClicked: ((Country) -> Unit)? = null

    inner class CountryVH(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val image = itemView.findViewById<TextView>(R.id.tv_emoji)
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)

        fun bind(item: Country) {

            tvName.text = item.name
            image.text = item.emoji

            binding.root.setOnClickListener {
                onItemClicked?.invoke(item)
            }
        }
    }
}