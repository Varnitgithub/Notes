package com.example.notes

import androidx.lifecycle.LiveData

class noteRepository(private val noteDao: noteDao) {

    var allNotes:LiveData<List<notes>> = noteDao.getallnotes()

    fun insert(notes: notes){
        noteDao.insert(notes)
    }
    fun delete(notes: notes){
        noteDao.delete(notes)
    }


}