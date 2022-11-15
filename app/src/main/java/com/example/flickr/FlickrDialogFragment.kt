package com.example.flickr

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class FlickrDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var message : EditText
        lateinit var search : Button
        lateinit var close : Button
        val extra = "msg"
        var v = LayoutInflater.from(context).inflate(R.layout.fragment_search_dialog,null,false)
        message = v.findViewById(R.id.query_et)
        search = v.findViewById(R.id.search_btn)
        close = v.findViewById(R.id.cancel_btn)
        search.setOnClickListener {
            var intent = Intent()
            intent.putExtra(extra,message.text.toString())
            targetFragment!!.onActivityResult(targetRequestCode, 0, intent)
            dismiss()
        }
        close.setOnClickListener {
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setView(v)
            .create()
    }
}