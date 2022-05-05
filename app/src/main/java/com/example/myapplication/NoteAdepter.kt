package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.note_layout.view.*


class NoteAdepter(context: Context,noteList:ArrayList<Note> )
    : ArrayAdapter<Note>(context,0, noteList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      val view= LayoutInflater.from(context).inflate(R.layout.note_layout,parent,false)
        var note: Note? =getItem(position)
        view.TitelTextView.text=note!!.titel
        view.DataTextView.text= note!!.timestamp.toString()
        return view
    }
}