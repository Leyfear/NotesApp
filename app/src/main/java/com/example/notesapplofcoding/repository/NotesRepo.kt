package com.example.notesapplofcoding.repository

import com.example.notesapplofcoding.db.DatabaseNote
import com.example.notesapplofcoding.db.Note

class NotesRepo(databaseNote: DatabaseNote) {

    val noteDao = databaseNote.noteDao
    suspend fun upsertNote(note: Note) = noteDao.upsertNote(note)
    suspend fun deleteNote(note:Note) = noteDao.deteleNote(note)
    fun getNotes() = noteDao.selectNotes()
    fun searchNotes(searchQuery: String) = noteDao.searchInNotesTitle(searchQuery)
    suspend fun deleteAllNotes() =  noteDao.deleteAllNotes()
}
