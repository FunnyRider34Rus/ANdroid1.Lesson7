package com.example.notes.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R

class NotesListAdapter : ListAdapter<Note, NotesListAdapter.NoteViewHolder>(NOTES_COMPARATOR) {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleNote: TextView? = itemView.findViewById(R.id.title)
        private val dateNote: TextView? = itemView.findViewById(R.id.date)

        fun bind(title: String?, date: String?) {
            if (titleNote != null) {
                titleNote.text = title
            }
            if (dateNote != null) {
                dateNote.text = date
            }
        }

        companion object {
            fun create(parent: ViewGroup): NoteViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
                return NoteViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title, current.createAt)
    }

    companion object {
        private val NOTES_COMPARATOR = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}