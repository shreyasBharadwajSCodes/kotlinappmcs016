package com.example.softwarelabapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast

class owlEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owl_entry)
        val acceptinput =  findViewById(R.id.acceptinput) as EditText
        val tw = object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().equals("owl",true)){
                    Toast.makeText(this@owlEntry,"Letting you in ... ",Toast.LENGTH_SHORT).show()
                    createIntentToMainPage()

                }
                else{
                    if(p0.toString().get(0).code.toLong() ==55358L) {
                        Toast.makeText(this@owlEntry, "Letting you in ... ", Toast.LENGTH_SHORT).show()
                        createIntentToMainPage()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
        acceptinput.addTextChangedListener(tw)
    }
    fun createIntentToMainPage(){
        val menuPage = Intent(this@owlEntry,MainPage::class.java)
        startActivity(menuPage)
    }
}