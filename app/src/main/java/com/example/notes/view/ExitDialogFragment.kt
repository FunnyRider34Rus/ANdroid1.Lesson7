package com.example.notes.view

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.notes.R

class ExitDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setMessage(R.string.exit_message)
            .setPositiveButton(R.string.yes) { dialog, which -> this.activity?.finish() }
            .setNegativeButton(R.string.no) { dialog, which -> super.onCreateDialog(savedInstanceState)}
            .setCancelable(false)
            .create()
        return alertDialogBuilder //super.onCreateDialog(savedInstanceState)
    }
}