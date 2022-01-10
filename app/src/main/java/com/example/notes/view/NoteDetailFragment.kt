package com.example.notes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.notes.MainActivity
import com.example.notes.R
import com.google.android.material.appbar.MaterialToolbar

class NoteDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        (activity as MainActivity).setToolbar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*when (item.itemId.toString()) {
            "back" ->
            "save" ->
        }*/
        return super.onOptionsItemSelected(item)
    }
}