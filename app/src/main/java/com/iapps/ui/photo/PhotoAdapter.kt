package com.iapps.ui.photo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iapps.data.remote.photo.response.PhotoItemResponse
import com.iapps.databinding.ItemPhotoBinding

class PhotoAdapter(private val context: Context, private val photoItems: List<PhotoItemResponse>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(layoutInflater)
        return PhotoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val photoItem = photoItems[position]
        // Bind data to views
        holder.bind(photoItem)
        holder.itemView.setOnClickListener {
            // Handle item click
            val uri = Uri.parse(photoItem.link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return photoItems.size
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoItem:PhotoItemResponse){

            Glide.with(binding.root).load(photoItem.media).into(binding.ivImage)

            binding.tvDescription.text = Html.fromHtml(photoItem.description, Html.FROM_HTML_MODE_COMPACT);

        }
    }
}