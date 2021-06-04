package com.example.megokinopoisk.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.megokinopoisk.R

class FilmsCollectionAdapter(private val context: Context) : RecyclerView.Adapter<FilmsCollectionAdapter.ViewHolder?>() {
    private var dataSource = arrayOf("Film1", "Film2", "Film3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.mego_kino_poisk_item_list_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FilmsCollectionAdapter.ViewHolder, position: Int) {
        holder.apply {
            name.text = dataSource[position]
            itemView.setOnClickListener {
                onItemClicked(dataSource[position])
            }
        }
    }

    private fun openDetailsFragment(item: String) {
        val manager = (context as AppCompatActivity?)!!.supportFragmentManager
        val bundle = Bundle()
        bundle.putString("title",item)
        manager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
    }

    private fun onItemClicked(item: String) {
        openDetailsFragment(item)
    }

    override fun getItemCount() = dataSource.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.image)

    }

    companion object {
        private const val TAG = "SocialNetworkAdapter"
    }
}