package com.example.notes.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.notes.R

class ExitDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setMessage(R.string.exit_message)
            .setPositiveButton(R.string.yes) { _dialog, _which -> activity?.finish() }
            .setNegativeButton(R.string.no) { _dialog, _which ->
                super.onCreateDialog(
                    savedInstanceState
                )
            }
            .setCancelable(false)
            .create()
    }
}