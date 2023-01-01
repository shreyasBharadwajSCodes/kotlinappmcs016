package com.example.softwarelabapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.softwarelabapp.databinding.ActivityMainPageBinding

class MainPage : AppCompatActivity() {
    lateinit var moArraylist:ArrayList<menuoptions>
    lateinit var binding:ActivityMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var ImageId = intArrayOf(
            R.drawable.owl_charm,
            R.drawable.owl_time,
            R.drawable.flyingowl,
            R.drawable.owl_mole_cover,
            R.drawable.owl_emotion_laughing
        )
        var MenuItems = arrayOf(
            "Introduction",
            "Owl time",
            "Owl moves",
            "Play owl mole",
            "Talking owl"
        )
        moArraylist = ArrayList()
        for(i in ImageId.indices){
            val mo = menuoptions(ImageId[i],MenuItems[i])
            moArraylist.add(mo)
            //Toast.makeText(this@MainPage,"Here"+i.toString(),Toast.LENGTH_SHORT).show()
        }


        binding.listview.adapter = MenuAdapter(this@MainPage,moArraylist)
        binding.listview.isClickable=true
        binding.listview.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this,MenuItems[i],Toast.LENGTH_SHORT).show()
            var intent:Intent
            if(MenuItems[i].equals("introduction",true)){
                intent = Intent(this@MainPage,LandingPage::class.java)
                startActivity(intent)
            }
            else if(MenuItems[i].equals("owl time",true)){
                intent = Intent(this@MainPage,OwlTimePage::class.java)
                startActivity(intent)
            }
            else if(MenuItems[i].equals("talking owl",true)){
                intent = Intent(this@MainPage,TalkOwlActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this@MainPage,MainActivity::class.java))
    }
}