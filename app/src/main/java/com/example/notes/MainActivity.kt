package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.view.NoteDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val transaction = "TRANSACTION"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { view ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NoteDetailFragment())
                .addToBackStack(transaction)
                .commit()
        }
    }
}