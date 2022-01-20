package com.example.notes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NoteDatabase {
            // если INSTANCE не пустой, то вернуть его
            // если пустой, то создать базу
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database.db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
                instance
            }
        }

        private class NoteDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //пересоздание базы
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.noteDAO())
                    }
                }
            }
        }

        //заполнение новой базы
        suspend fun populateDatabase(noteDao: NoteDao) {

            var note = Note(UUID.randomUUID().toString(), "Title1", "Message1", Date().toString())
            noteDao.insert(note)

            note = Note(UUID.randomUUID().toString(), "Title2", "Message2", Date().toString())
            noteDao.insert(note)
        }
    }
}