package com.example.notes

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [notes::class], version = 1, exportSchema = false)
abstract class noteDataBase : RoomDatabase() {
    abstract fun notedao(): noteDao

    companion object {

        @Volatile
        var INSTANCE: noteDataBase? = null
        fun getdata(context: Context): noteDataBase? {
           if (INSTANCE==null){
              synchronized(this){
                  INSTANCE = Room.databaseBuilder(context.applicationContext
                      ,noteDataBase::class.java,"roomDb").build()
              }
            }
            return INSTANCE!!
            }
        }
    }



