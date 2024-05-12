package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
//        when (data[position].priority.toLowerCase()) {
//            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
//            "low" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
//            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
//        }
        // Set text color of priority TextView based on priority
//        when (data[position].priority.toLowerCase()) {
//            "high" -> holder.priority.setTextColor(Color.parseColor("#FF0000")) // Red color for high priority
//            "low" -> holder.priority.setTextColor(Color.parseColor("#00FF00")) // Green color for medium priority
//            else -> holder.priority.setTextColor(Color.parseColor("#000000")) // Black color for low priority
//        }
        // Set text color of priority TextView based on priority
        when (data[position].priority.toLowerCase()) {
            "high" -> {
                holder.priority.setTextColor(Color.parseColor("#FF0000")) // Red color for high priority
                holder.priority.text = "\u2022 ${data[position].priority}" // Add bullet point before priority text
            }
            "low" -> {
                holder.priority.setTextColor(Color.parseColor("#00FF00")) // Green color for medium priority
                holder.priority.text = "\u2022 ${data[position].priority}" // Add bullet point before priority text
            }
            else -> {
                holder.priority.setTextColor(Color.parseColor("#000000")) // Black color for low priority
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


