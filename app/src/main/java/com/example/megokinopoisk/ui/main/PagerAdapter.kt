package layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.megokinopoisk.R

class PagerAdapter(private val context: Context, private val words: List<String>) :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.page_view, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.textView.text = words[position]
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }
}