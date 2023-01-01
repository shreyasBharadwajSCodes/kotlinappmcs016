package com.example.softwarelabapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.example.softwarelabapp.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val speakowl = findViewById(R.id.speakowl) as ImageView
        binding.speakowl.visibility = View.GONE
        //val actclick = findViewById(R.id.actclick) as Button
        //val owlpic = findViewById(R.id.owlpic) as ImageView

        binding.owlpic.setOnClickListener {
            Toast.makeText(this@MainActivity,"Don't infuriate the owl",Toast.LENGTH_SHORT).show()
            binding.owlpic.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.angry_red), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.speakowl.visibility = View.VISIBLE
            val handler: Handler = Handler()
            handler.postDelayed({ binding.owlpic.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.purple_200), android.graphics.PorterDuff.Mode.MULTIPLY);
            }, 1000)
            handler.postDelayed({ binding.owlpic.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.angry_red), android.graphics.PorterDuff.Mode.MULTIPLY);
            }, 2000)
            handler.postDelayed({ binding.owlpic.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                                binding.speakowl.visibility=View.GONE}, 3000)
            //handler.postDelayed({ owlpic.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.angry_red), android.graphics.PorterDuff.Mode.MULTIPLY); }, 1000)
        }

        binding.actclick.setOnClickListener {
            Toast.makeText(this@MainActivity,"Taking you to the next page",Toast.LENGTH_SHORT).show()

            val goToMainPage:Intent = Intent(this@MainActivity, MainPage::class.java)
            startActivity(goToMainPage)
        }

    }

}