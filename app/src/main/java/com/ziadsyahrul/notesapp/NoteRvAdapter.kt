package com.ziadsyahrul.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRvAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
): RecyclerView.Adapter<NoteRvAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val noteTv = itemView.findViewById<TextView>(R.id.TVNoteTitle)
        val timeTv = itemView.findViewById<TextView>(R.id.TVTimeStamp)
        val deleteTv = itemView.findViewById<ImageView>(R.id.IVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTv.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("Last updated : " +allNotes.get(position).timeStamp)

        holder.deleteTv.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}