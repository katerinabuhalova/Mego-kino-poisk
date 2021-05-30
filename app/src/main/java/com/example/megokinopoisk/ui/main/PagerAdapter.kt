package layout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.megokinopoisk.R
import com.example.megokinopoisk.ui.main.DetailsFragment

class PagerAdapter(private val context: Context, private val words: List<String>) :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.page_view, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.textView.text = words[position]
        holder.textView.setOnClickListener{ openDetailsFragment() }
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.textView)
    }


   private fun openDetailsFragment() {
       val manager = (context as AppCompatActivity?)!!.supportFragmentManager
       val bundle = Bundle()
       manager.beginTransaction()
           .replace(R.id.container, DetailsFragment.newInstance(bundle))
           .addToBackStack("")
           .commitAllowingStateLoss()

   }
}