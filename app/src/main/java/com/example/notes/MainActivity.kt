package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.notes.view.ExitDialogFragment
import com.example.notes.view.NoteDetailFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val transaction = "TRANSACTION"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)

        setToolbar(toolbar)

        fab.setOnClickListener { _view ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NoteDetailFragment())
                .addToBackStack(transaction)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId.toString()) {
            "exit" -> this.finish()
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
        super.onBackPressed()
    }
}