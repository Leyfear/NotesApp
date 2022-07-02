package com.example.notesapplofcoding.repository

import com.example.notesapplofcoding.db.DatabaseNote
import com.example.notesapplofcoding.db.Note

class NotesRepo(databaseNote: DatabaseNote) {

    val noteDao = databaseNote.noteDao.also { println("asd1") }
    suspend fun upsertNote(note: Note) = noteDao.upsertNote(note).also { println("asd2") }
    suspend fun deleteNote(note:Note) = noteDao.deteleNote(note).also { println("asd3") }
    fun getNotes() = noteDao.selectNotes().also { println("bunu") }
    fun searchNotes(searchQuery: String) = noteDao.searchInNotesTitle(searchQuery)
    suspend fun deleteAllNotes() =  noteDao.deleteAllNotes()
}