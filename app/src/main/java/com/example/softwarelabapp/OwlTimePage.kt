package com.example.softwarelabapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.softwarelabapp.databinding.ActivityOwlTimePageBinding


class OwlTimePage : AppCompatActivity() {
    private lateinit var binding:ActivityOwlTimePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwlTimePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(OwlTimeHomeFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.hometime -> replaceFragment(OwlTimeHomeFragment())
                R.id.clock -> replaceFragment(ClockOwlFragment())
                R.id.stopwatch -> replaceFragment(StopWatchOwlFragment())
                else->{

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        val fragmentmanager = supportFragmentManager
        val fragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
        return true
    }
}