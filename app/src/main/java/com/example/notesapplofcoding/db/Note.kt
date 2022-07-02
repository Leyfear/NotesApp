package com.example.notesapplofcoding.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.RowId

@Entity
@Parcelize
//Entitynin içine isim vermezsek otomatik olarak class ismimiz table ismi oluyor.
data class Note(

    //@ColumnInfo(name = "noteId") şeklinde kendi kolon ismimizi verebiliriz
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val noteTitle : String,
    val noteText : String
):Parcelable
