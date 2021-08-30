package com.note11.schoolinfoapp.util

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.note11.schoolinfoapp.R

class HintSpinnerAdapter(context: Context, private val hint: String) :
    ArrayAdapter<String>(context, R.layout.item_spinner) {

    init {
        setNotifyOnChange(true)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return if (position != 0) super.getView(position, convertView, parent)
        else {
            val view = super.getView(position, convertView, parent)

            view.findViewById<TextView>(R.id.txt_spinner).run {
                text = ""
                hint = this@HintSpinnerAdapter.hint
            }

            view
        }
    }

    fun setList(list: List<String>) {
        clear()
        add(hint)
        addAll(list.toMutableList())
    }
}