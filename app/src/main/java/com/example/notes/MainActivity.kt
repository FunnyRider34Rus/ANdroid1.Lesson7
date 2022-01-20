package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.model.NotesListAdapter
import com.example.notes.view.ExitDialogFragment
import com.example.notes.view.NoteDetailFragment
import com.example.notes.view.NotesListFragment
import com.example.notes.viewmodel.NoteViewModel
import com.example.notes.viewmodel.NoteViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val transaction = "TRANSACTION"

class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by viewModels { NoteViewModelFactory((application as App).repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, NotesListFragment())
            .addToBackStack(transaction)
            .commit()

        val fab: FloatingActionButton = findViewById(R.id.fab)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = NotesListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        setToolbar(toolbar)

        fab.setOnClickListener { _view ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NoteDetailFragment())
                .addToBackStack(transaction)
                .commit()
        }

        noteViewModel.getNotes.observe(this) { notes ->
            notes.let { adapter.submitList(it) }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId.toString()) {
            "exit" -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setToolbar(toolbar: MaterialToolbar) {
        setSupportActionBar(toolbar)
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        val exitDialog = ExitDialogFragment()
        exitDialog.show(supportFragmentManager, "exitDialog")
    }
}