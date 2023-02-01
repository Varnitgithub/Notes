package com.example.notes

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), noteInterface {
    private lateinit var viewModel: noteViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this, ViewModelProvider
                .AndroidViewModelFactory.getInstance(application)
        )[noteViewModel::class.java]
        //viewModel = ViewModelProvider(this).get(noteViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = noteAdapter(this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allnotes.observe(this) {
            adapter.updateitem(it)
        }
    }
    override fun onNoteClick(notes: notes) {
        viewModel.delete(notes)
        Toast.makeText(this, "$notes Deleted", Toast.LENGTH_SHORT).show()
    }
    fun submit(view: View) {
        val editnote: EditText = findViewById(R.id.edittext)
        val getnote = editnote.text.toString()
        if (getnote.isNotEmpty()) {
            viewModel.insert(notes(getnote))
            Toast.makeText(this, "$getnote Inserted", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "Please add a Note", Toast.LENGTH_SHORT).show()
        editnote.text.clear()
    }
}