package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class noteAdapter(private val context:Context, private val listener: noteInterface) :
    RecyclerView.Adapter<noteAdapter.noteViewHolder>() {

     private val noteItem = ArrayList<notes>()

    inner class noteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text: TextView = itemView.findViewById(R.id.textview)
        val deleteimage: ImageView = itemView.findViewById(R.id.deleteimage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        val viewholder = noteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
        viewholder.deleteimage.setOnClickListener {
            listener.onNoteClick(noteItem[viewholder.adapterPosition])
        }
        return viewholder
    }
    override fun getItemCount(): Int {
        return noteItem.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateitem(newList:List<notes>){
        noteItem.clear()
        noteItem.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val currentItem = noteItem[position]
        holder.text.text = currentItem.note
    }
}
    interface noteInterface {
        fun onNoteClick(notes: notes)
    }