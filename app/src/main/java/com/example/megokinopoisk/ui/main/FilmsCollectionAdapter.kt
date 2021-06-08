package com.example.megokinopoisk.ui.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.megokinopoisk.R
import com.example.megokinopoisk.data.DataSource
import com.example.megokinopoisk.data.FilmDetailsDTO

class FilmsCollectionAdapter(private val context: Context) : RecyclerView.Adapter<FilmsCollectionAdapter.ViewHolder?>() {
    private var filmsCollection: ArrayList<FilmDetailsDTO> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        var dataSource = DataSource(onLoadListener).also {
           it.loadData()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.mego_kino_poisk_item_list_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FilmsCollectionAdapter.ViewHolder, position: Int) {
        holder.apply {
            name.text = filmsCollection[position].name
            description.text = filmsCollection[position].description
            itemView.setOnClickListener {
                onItemClicked(filmsCollection[position])
            }
        }
    }

    private fun openDetailsFragment(item: FilmDetailsDTO) {
        val manager = (context as AppCompatActivity?)!!.supportFragmentManager
        val bundle = Bundle()
        bundle.putSerializable("film", item)
        manager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
    }

    private fun onItemClicked(item: FilmDetailsDTO) {
        openDetailsFragment(item)
    }

    override fun getItemCount() = filmsCollection.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.image)

    }

    companion object {
        private const val TAG = "SocialNetworkAdapter"
    }

    private val onLoadListener: DataSource.FilmLoaderListener =
            object : DataSource.FilmLoaderListener {

                override fun onLoaded(filmDetailsDTO: FilmDetailsDTO) {
                    filmsCollection.add(filmDetailsDTO)
                    notifyDataSetChanged()
                }

                override fun onFailed(throwable: Throwable) {
                }
            }
}