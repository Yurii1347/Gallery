package com.vytivskyi.gallery.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vytivskyi.gallery.R
import com.vytivskyi.gallery.database.dto.PhotoDtoItem
import com.vytivskyi.gallery.databinding.RecyclePhotoBinding

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    var mainL: List<PhotoDtoItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: (id: String) -> Unit = {}

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecyclePhotoBinding.bind(view)

        fun bind(image: String, title: String, author: String) = with(binding) {
            Picasso.get().load(image)
                .resize(100, 100)
                .centerCrop()
                .into(photo)
            titleOfPhoto.text = title
            authorOfPhoto.text = author
        }

        init {
            itemView.setOnClickListener {
                itemClickListener(mainL[absoluteAdapterPosition].urls.regular)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(
            mainL[position].urls.regular,
            mainL[position].description ?: "have no title",
            mainL[position].user.name
        )

    }

    override fun getItemCount() = mainL.size
}