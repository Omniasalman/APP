package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_note.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var mREf : DatabaseReference ?= null
    var mnoteList:ArrayList<Note>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Database :FirebaseDatabase =FirebaseDatabase.getInstance()
        mREf= Database.getReference("Note")
        mnoteList= ArrayList()

       addNew.setOnClickListener{
           showDialogeAddNote()
       }
    }

    override fun onStart() {
        super.onStart()
        mREf?.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                mnoteList?.clear()
                for(n in snapshot!!.children){
                    var note =n.getValue(Note::class.java)
                    mnoteList!!.add(note!!)
                }
                val noteadepter=NoteAdepter(applicationContext,mnoteList!!)
                listView.adapter=noteadepter
            }



        })
    }


    fun showDialogeAddNote(){
        val alertBuilder= AlertDialog.Builder(this)
        val view =layoutInflater.inflate(R.layout.add_note,null)

        alertBuilder.setView(view)
        val alertDialog =alertBuilder.create()
        alertDialog.show()

        view.saveNote.setOnClickListener{
            val title=view.editText.text.toString()
            val note=view.editText2.text.toString()
            if (title.isNotEmpty() &&note.isNotEmpty() ){
               var id = mREf!!.push().key
                var myNote= Note(id, title, note ,getCurrent())
                mREf!!.child(id).setValue(myNote)
                alertDialog.dismiss()

            }else {
                Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
            }

        }

    }
    fun getCurrent():String{
        val calender=Calendar.getInstance()
        val ndformat =SimpleDateFormat("EEE hh:mm a")
        val strDate=ndformat.format(calender.time)
        return strDate
    }
}