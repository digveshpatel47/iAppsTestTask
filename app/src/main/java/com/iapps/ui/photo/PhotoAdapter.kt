package com.iapps.ui.photo

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iapps.data.photo.remote.response.PhotoModel
import com.iapps.databinding.ItemPhotoBinding
import com.iapps.exts.fromHtml
import com.iapps.exts.loadImage

class PhotoAdapter : ListAdapter<PhotoModel, PhotoAdapter.DataViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(layoutInflater)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind()
    }

    inner class DataViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            binding.ivImage.loadImage(binding.root.context, item.media?.m)
            binding.tvDescription.text =item.description?.fromHtml()
            binding.cvMain.setOnClickListener {
                try {
                    it.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(item.link)
                        ), null
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object : DiffUtil.ItemCallback<PhotoModel>() {
        override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
            return oldItem.title == newItem.title
        }
    }

}