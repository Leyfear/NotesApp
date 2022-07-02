package com.example.notesapplofcoding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import com.example.notesapplofcoding.R
import com.example.notesapplofcoding.db.DatabaseNote
import com.example.notesapplofcoding.repository.NotesRepo
import com.example.notesapplofcoding.viewmodel.NotesViewModel
import com.example.notesapplofcoding.viewmodel.providerFactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    val viewModel :NotesViewModel by lazy {
        val database = DatabaseNote.getDatabaseInstance(this)
        val notesRepo = NotesRepo(database)
        val notesProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this,notesProviderFactory)[NotesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}