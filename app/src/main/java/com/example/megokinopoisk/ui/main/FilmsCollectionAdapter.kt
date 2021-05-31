package com.example.megokinopoisk.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.megokinopoisk.R

class FilmsCollectionAdapter() : RecyclerView.Adapter<FilmsCollectionAdapter.ViewHolder?>() {
    private var dataSource: Array<String> = arrayOf("Film1", "Film2", "Film3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.mego_kino_poisk_item_list_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FilmsCollectionAdapter.ViewHolder, position: Int) {
        holder.name.text = dataSource[position]
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.image)

    }

    companion object {
        private const val TAG = "SocialNetworkAdapter"
    }
}