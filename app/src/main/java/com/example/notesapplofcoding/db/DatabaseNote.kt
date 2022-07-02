package com.example.notesapplofcoding.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 4)
abstract class DatabaseNote:RoomDatabase() {
    abstract val noteDao : NoteDao

    companion object{
        @Volatile
        var INSTANCE : DatabaseNote? = null

        @Synchronized
        fun getDatabaseInstance(context: Context):DatabaseNote{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    DatabaseNote::class.java,
                    "Database").fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!

        }



    }
}