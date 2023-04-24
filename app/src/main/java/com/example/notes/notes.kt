package com.example.notes

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity("Note_Table")
class notes(var note:String, val hour:String,val minutes:String){
    @PrimaryKey(autoGenerate = true)var id =0
}

