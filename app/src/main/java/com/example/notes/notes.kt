package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("Note_Table")
class notes(var note:String){
    @PrimaryKey(autoGenerate = true)var id =0
}

