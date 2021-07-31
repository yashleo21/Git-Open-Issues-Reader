package com.yash2108.openissuesreader.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.databinding.ItemCommentBinding
import com.yash2108.openissuesreader.databinding.ItemListBinding
import com.yash2108.openissuesreader.models.DetailDataObject
import java.text.SimpleDateFormat
import javax.inject.Inject

class CommentsAdapter @Inject constructor(val callback: Callback) : ListAdapter<DetailDataObject, CommentsAdapter.ItemViewHolder>(HomeDiffUtil()) {

    private val TAG = CommentsAdapter::class.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(getItem(position), position)
    }

    inner class ItemViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root){

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


        fun bindView(data: DetailDataObject, position: Int) {
            binding.tvComment.text = data.body
        }
    }

    class HomeDiffUtil(): DiffUtil.ItemCallback<DetailDataObject>() {
        override fun areItemsTheSame(oldItem: DetailDataObject, newItem: DetailDataObject): Boolean {
            return oldItem.body == newItem.body
        }

        override fun areContentsTheSame(oldItem: DetailDataObject, newItem: DetailDataObject): Boolean {
            return oldItem == newItem
        }
    }

    interface Callback {
        fun onItemClicked(data: DetailDataObject, position: Int, transitionView: View)
    }
}