package com.example.notes

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), noteInterface {
    private lateinit var viewModel: noteViewModel
    lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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


val button:FloatingActionButton = findViewById(R.id.addbottonfordialog)
        button.setOnClickListener {
            dialog()
        }
    }

    override fun onNoteClick(notes: notes) {
        viewModel.delete(notes)
        Toast.makeText(this, "${notes.note} Deleted", Toast.LENGTH_SHORT).show()
    }


    private fun dialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        val savebutton: Button = dialog.findViewById(R.id.save_Button)
        savebutton.setOnClickListener {
            val notetext: EditText = dialog.findViewById(R.id.edittext)
            val mynote = notetext.text.toString()
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            viewModel.insert(notes(mynote, hour.toString(), minute.toString()))
            dialog.dismiss()

        }
        val cancelbutton = dialog.findViewById<Button>(R.id.cancel_Button)
        cancelbutton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}