package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface noteDao {

    @Insert(onConflict = (OnConflictStrategy.IGNORE))
    fun insert(notes: notes)

    @Delete
    fun delete(notes: notes)

    @Update
    fun update(notes: notes)

    @Query("Select * from Note_table")
    fun getallnotes():LiveData<List<notes>>

}