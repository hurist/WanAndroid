package com.hurist.wanandroid.ui.adapter

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hurist.wanandroid.R
import com.hurist.wanandroid.data.responseData.Article

class HomePageArticleAdapter(
    private val context: Context
): RecyclerView.Adapter<HomePageArticleAdapter.CustomViewHolder>() {

    private val data = arrayListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(newData: List<Article>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: List<Article>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun  bind(article: Article) {
            tvTitle.text = article.title
        }
    }
}