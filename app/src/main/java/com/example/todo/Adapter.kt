package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.view.*

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var priority = itemView.priority
        var description = itemView.description
        var deadline = itemView.deadline
        var layout = itemView.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        // Set text color of priority TextView based on priority
        when (data[position].priority.toLowerCase()) {
            "high" -> {
                holder.priority.setTextColor(Color.parseColor("#FF0000")) // Red color for high priority

            }
            "low" -> {
                holder.priority.setTextColor(Color.parseColor("#00FF00")) // Green color for law priority

            }
            else -> {
                holder.priority.setTextColor(Color.parseColor("#000000")) // Black color for medium priority
                holder.priority.text = data[position].priority
            }
        }

        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.description.text = data[position].description
        holder.deadline.text = data[position].deadline


        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}