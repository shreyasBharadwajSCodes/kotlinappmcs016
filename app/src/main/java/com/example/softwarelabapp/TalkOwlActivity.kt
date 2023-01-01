package com.example.softwarelabapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.util.*

class TalkOwlActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    var speaking = false
    var rx = 10f
    lateinit var et:EditText
    lateinit var tts:TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talk_owl)
        //speaking = false
        et = findViewById<EditText>(R.id.text_value_speech)
        makeOwlSpeak()

        val go = findViewById<Button>(R.id.buttonspeak)
        go.setOnClickListener {
            when(isOwlSpeaking()) {
                true -> {
                    stopOwlSpeaking()
                }

                else -> {
                    speaking = true
                    tts =  TextToSpeech(applicationContext,TextToSpeech.OnInitListener {
                        if(it==TextToSpeech.SUCCESS){
                            tts.language = Locale.ENGLISH
                            tts.setSpeechRate(1f)
                            makeOwlSpeak()
                            val s = et.text.toString()
                                for(words in s.split("\\p{Punct}".toRegex())) {
                                    makeOwlSpeak()
                                    tts.setSpeechRate((1f+rx*0.01).toFloat())
                                    tts.speak(words,TextToSpeech.QUEUE_ADD,null)

                                }
                            speaking = false
                            stopOwlSpeaking()
                        }
                    })

                }
            }
        }
    }

    fun makeOwlSpeak(){
        var img = findViewById(R.id.speakowl) as ImageView
        if(!isOwlSpeaking()){
            img.setImageResource(R.drawable.owl_closed_mouth)
            return
        }

            Handler(Looper.getMainLooper()).postDelayed({
                img.setImageResource(R.drawable.owl_closed_mouth)
                img.animate().apply {
                    duration = (300+rx).toLong()
                    rotationXBy(rx)

                }
                img.animate().apply {
                    duration =100-rx.toLong()
                    rotationYBy(-rx)

                }
            }, 500)
            Handler(Looper.getMainLooper()).postDelayed({
                img.setImageResource(R.drawable.owl_open_mouth)
                img.animate().apply {
                    duration =(300+rx).toLong()
                    rotationXBy(rx)

                }
                img.animate().apply {
                    duration =100-rx.toLong()
                    rotationYBy(-rx)

                }
                rx = -rx
                when(isOwlSpeaking()){
                    true -> makeOwlSpeak()
                        else ->{

                        }
                }
            }, 1000)
    }
    fun isOwlSpeaking(): Boolean {
        return speaking
    }
    fun stopOwlSpeaking(){
        speaking = false
    }

    override fun onInit(p0: Int) {

    }

    override fun onDestroy(){
        super.onDestroy()
        tts.shutdown()
    }
}