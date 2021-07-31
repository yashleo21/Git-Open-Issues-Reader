package com.yash2108.openissuesreader.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.databinding.ItemListBinding
import java.text.SimpleDateFormat
import javax.inject.Inject

class HomeAdapter @Inject constructor(val callback: Callback) : ListAdapter<HomeDataObject, HomeAdapter.ItemViewHolder>(HomeDiffUtil()) {

    private val TAG = HomeAdapter::class.simpleName

    var input: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    var output: SimpleDateFormat = SimpleDateFormat("MM-dd-yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(getItem(position), position)
    }

    inner class ItemViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        init {
            attachListeners()
        }

        private fun attachListeners() {
            binding.root.setOnClickListener {
                if (adapterPosition < 0) return@setOnClickListener
                val position = adapterPosition

                callback.onItemClicked(getItem(position), position, binding.root)
            }
        }


        fun bindView(data: HomeDataObject, position: Int) {
            //Set title
            if (data.title?.isNotBlank() == true) {
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvTitle.text = data.title
            } else {
                binding.tvTitle.visibility = View.GONE
            }

            //Subtitle
            if (data.body?.isNotBlank() == true) {
                binding.tvDesc.visibility = View.VISIBLE
                binding.tvDesc.text = data.body
            } else {
                binding.tvDesc.visibility = View.GONE
            }

            //Date
            if (data.updated_at?.isNotBlank() == true) {
                binding.tvDate.visibility = View.VISIBLE
                val formattedDate = input.parse(data.updated_at)
                binding.tvDate.text = output.format(formattedDate)
            } else {
                binding.tvDate.visibility = View.GONE
            }

            //User info
            if (data.user?.avatar_url?.isNotBlank() == true) {
                binding.ivAvatar.visibility = View.VISIBLE

                Glide.with(binding.ivAvatar)
                    .load(data.user?.avatar_url)
                    .into(binding.ivAvatar)
            } else {
                binding.ivAvatar.visibility = View.GONE
            }

            if (data.user?.login?.isNotBlank() == true) {
                binding.tvUserName.visibility = View.VISIBLE
                binding.tvUserName.text = data.user?.login
            } else {
                binding.tvUserName.visibility = View.GONE
            }

        }
    }

    class HomeDiffUtil(): DiffUtil.ItemCallback<HomeDataObject>() {
        override fun areItemsTheSame(oldItem: HomeDataObject, newItem: HomeDataObject): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: HomeDataObject, newItem: HomeDataObject): Boolean {
            return oldItem == newItem
        }
    }

    interface Callback {
        fun onItemClicked(data: HomeDataObject, position: Int, transitionView: View)
    }
}