package com.example.notesapplofcoding.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplofcoding.databinding.NotesItemBinding
import com.example.notesapplofcoding.db.Note

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NotesHolder>() {
    var onClick : ((Note) -> Unit)? = null

    class NotesHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.tvNoteTitle.text = note.noteTitle
        }
    }

    private val diff = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
          return oldItem.noteId == newItem.noteId
        }
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diff)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            onClick?.invoke(note)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}