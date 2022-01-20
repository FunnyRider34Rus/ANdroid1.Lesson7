package com.example.notes

import android.app.Application
import com.example.notes.model.NoteDatabase
import com.example.notes.model.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App  : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.noteDAO()) }
}