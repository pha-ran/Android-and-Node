package com.example.client_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(val datalist : ArrayList<resultData>, val context: Context)
    : RecyclerView.Adapter<adapter.viewholder>(){

    class viewholder(view : View) : RecyclerView.ViewHolder(view) {

        val id = view.findViewById<TextView>(R.id.textView)
        val title = view.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val item = LayoutInflater.from(context).inflate(R.layout.item, parent, false)

        return viewholder(item)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.id.text = datalist[position].getid()
        holder.title.text = datalist[position].gettitle()
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}