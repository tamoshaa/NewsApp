package com.example.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListAdapter(val listener : NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
    private var  items : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val holder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.itemClicked(items[holder.bindingAdapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = "Description : "+currentItem.title
        holder.author.text = "Author : "+currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }
    fun update(updatedItems : ArrayList<News>){
        items.clear()
        items.addAll(updatedItems)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.title
    val author : TextView = itemView.author
    val image :ImageView = itemView.image
}
interface NewsItemClicked{
    fun itemClicked(news : News)
}