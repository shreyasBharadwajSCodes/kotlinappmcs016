package com.example.softwarelabapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MenuAdapter (private val context: Activity, private val arraylist:ArrayList<menuoptions>) :
    ArrayAdapter<menuoptions>(context,R.layout.menu_list,arraylist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflator.inflate(R.layout.menu_list,null)
        val imageview:ImageView = view.findViewById(R.id.menu1img)
        val textview:TextView = view.findViewById(R.id.menu1text)

        imageview.setImageResource(arraylist[position].imageid)
        textview.text=arraylist[position].message

        return view
    }

}