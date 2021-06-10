package layout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megokinopoisk.R
import com.example.megokinopoisk.ui.main.DetailsFragment
import com.example.megokinopoisk.ui.main.FilmsCollectionAdapter
import java.io.File.separator
import java.security.AccessController.getContext

class PagerAdapter(private val context: Context, private val words: List<String>) :
        RecyclerView.Adapter<PagerAdapter.PageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        return PageHolder(LayoutInflater.from(context).inflate(R.layout.page_view, parent, false))
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.recyclerView.setHasFixedSize(false)
        val layoutManager = LinearLayoutManager(context)
        holder.recyclerView.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
            setDrawable(context.resources.getDrawable(R.drawable.separator))
        }
        holder.recyclerView.addItemDecoration(itemDecoration)

        var adapter = FilmsCollectionAdapter(context)
        holder.recyclerView.adapter = adapter
    }

    override fun getItemCount() = words.size

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_lines)
    }
}