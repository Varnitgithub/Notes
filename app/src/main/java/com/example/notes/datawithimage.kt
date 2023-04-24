package com.example.notes

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Note_ImageTable")
data class datawithimage(val mynote:String,val imageView: ImageView){
    @PrimaryKey(autoGenerate = true)var id =0
}
