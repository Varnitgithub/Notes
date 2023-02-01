package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [notes::class], version = 1, exportSchema = false)
abstract class noteDataBase : RoomDatabase() {
    abstract fun notedao(): noteDao

    companion object {
        @Volatile
        var INSTANCE: noteDataBase? = null
        fun getdata(context: Context): noteDataBase {
           if (INSTANCE==null){
              synchronized(this){
                  INSTANCE = Room.databaseBuilder(context.applicationContext,noteDataBase::class.java,"roomDb").build()
              }
            }
            return INSTANCE!!
        }
    }
}


