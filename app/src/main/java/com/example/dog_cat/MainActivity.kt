package com.example.dog_cat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dog_cat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                //바텀 메뉴바 구현 (id => item 아이디  ,매계변수 -> 바꿀 액티비트)
                R.id.home -> replaceFragment(Home())
                R.id.calender -> replaceFragment(Calender())
                R.id.youtube -> replaceFragment(youtube())
                R.id.map -> replaceFragment(map_frg())



                else ->{

                }


            }

            true

        }

    }
    //구현 코드
    private fun replaceFragment(fragmet : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragmet)
        fragmentTransaction.commit()
    }


}