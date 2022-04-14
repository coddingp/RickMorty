package com.company.rickmorty.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.rickmorty.R
import com.company.rickmorty.main.models.character.Result

class RickMortyAdapter() :
    RecyclerView.Adapter<RickMortyAdapter.MainViewHolder>() {


    private val data = mutableListOf<Result>()

    inner class MainViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val textname: TextView = itemView.findViewById(R.id.main_text_view_1)
        private val number: TextView = itemView.findViewById(R.id.text_view1)
        private val number2: TextView = itemView.findViewById(R.id.text_view2)
        fun bind(listItem: Result) {
            textname.text = listItem.name
            number.text = listItem.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val listItem = data[position]
        holder.bind(listItem)
    }

    override fun getItemCount() =
        data.size

}