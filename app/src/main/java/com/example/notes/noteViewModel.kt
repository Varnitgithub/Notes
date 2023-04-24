package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class noteViewModel(application: Application) : AndroidViewModel(application) {
private var repository: noteRepository
    val allnotes:LiveData<List<notes>>
            init{
                val dataDao = noteDataBase.getdata(application)!!.notedao()
                 repository = noteRepository(dataDao)
                allnotes = repository.allNotes
            }
    fun delete(notes: notes) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(notes)
    }
    fun insert(notes: notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(notes)
    }
}