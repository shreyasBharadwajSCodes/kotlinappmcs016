@file:Suppress("DEPRECATION")

package com.example.softwarelabapp

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintLayout
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import com.example.softwarelabapp.databinding.ActivityLandingPageBinding
import kotlin.random.Random

class LandingPage : AppCompatActivity() {
    var buttonnew:Button? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var lpbinder = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(lpbinder.root)
        val owlwords = resources.getStringArray(R.array.owl_words)
        lpbinder.flyingowl.animate().apply{
            duration=1000
            rotationYBy(360f)
            translationYBy(40f)
        }
        lpbinder.chatwithowl.apply{
            setTextSize(1, 25F)
            text = "\n"
            setTextColor(resources.getColor(R.color.speak_text))
        }
        lpbinder.lpAll.setOnClickListener {
            val t= (lpbinder.chatwithowl.text.toString() + "<font COLOR=\'RED\'><b>" +"<br>* Don't Click on anything while I'm explaining stuff!"+"</b></font>")
            lpbinder.chatwithowl.setText(Html.fromHtml(t))
            //lpbinder.chatwithowl.text =  (lpbinder.chatwithowl.text.toString() + "<font COLOR=\'RED\'><b>" +"\n* Don't Click on anything while I'm explaining stuff!"+"</b></font>") as SpannableString
        }
        lpbinder.scroll.post {
            lpbinder.scroll.fullScroll(ScrollView.FOCUS_DOWN)
        }
        addButton(lpbinder)
        Handler(Looper.getMainLooper()).postDelayed({printallSentences(lpbinder,owlwords)},1500)
    }
    fun printallSentences(lpbinder:ActivityLandingPageBinding,owlwords:Array<String>){
        var times=0
        Handler(Looper.getMainLooper()).postDelayed({
        for(sentence in owlwords){
            Handler(Looper.getMainLooper()).postDelayed( {
                lpbinder.chatwithowl.apply {
                    text = lpbinder.chatwithowl.text.toString() + sentence
                }
                lpbinder.scroll.post {
                    lpbinder.scroll.fullScroll(ScrollView.FOCUS_DOWN)
                }
            }, (1010*times).toLong())
            times = times + 1
        }
            Handler(Looper.getMainLooper()).postDelayed({
            buttonnew?.visibility=View.VISIBLE
            buttonnew?.isEnabled = true}, (1010*times).toLong())
                                                    },100)
    }
    fun addButton(lpbinder:ActivityLandingPageBinding){
        val transferbutton:Button = Button(this@LandingPage)
        val params = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topToBottom=R.id.chatwithowl;
        transferbutton.apply{
            id = Random.nextInt()
            isEnabled = true
            layoutParams = params
            text = "Embark on an adventure!"
            setTextColor(resources.getColor(R.color.yellow_golden))
            setBackgroundColor(resources.getColor(R.color.background_button))
        }
        transferbutton.visibility = View.INVISIBLE
        buttonnew = transferbutton
        lpbinder.lpAll.addView(transferbutton)
        transferbutton.setOnClickListener{
            transferbutton.isEnabled=false
            //Animate OWL
            lpbinder.chatwithowl.visibility=View.INVISIBLE
            lpbinder.flyingowl.animate().apply{
                duration=1500
                rotationXBy(-180f) //Reverse the owl
                translationYBy(lpbinder.lpAll.height.toFloat()*15/25)
                scaleYBy(0.5f)
                scaleXBy(0.3f)
            }
            val colorAnimation =
                ValueAnimator.ofObject(ArgbEvaluator(), Color.WHITE, Color.DKGRAY)
            colorAnimation.duration = 100L
            colorAnimation.addUpdateListener { animator -> lpbinder.root.setBackgroundColor(animator.animatedValue as Int) }
            colorAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    lpbinder.flyingowl.animate().apply{
                        duration=1000
                        rotationYBy(540f)
                        scaleYBy(0.5f)
                        scaleXBy(0.6f)
                    }
                    lpbinder.scroll.setBackgroundColor(resources.getColor(R.color.black))

                },2000)
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    val colorAnimation =
                        ValueAnimator.ofObject(ArgbEvaluator(), Color.DKGRAY, Color.BLACK)
                    colorAnimation.duration = 1000L
                    colorAnimation.addUpdateListener { animator -> lpbinder.root.setBackgroundColor(animator.animatedValue as Int) }
                    colorAnimation.start()
                    transferbutton.text="Let's Go!"
                },3000)
            //Create an Intent to move on to adventure
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    val gotoowlentry = Intent(this@LandingPage,owlEntry::class.java)
                    startActivity(gotoowlentry)
                },4500
            )
        }

    }
}