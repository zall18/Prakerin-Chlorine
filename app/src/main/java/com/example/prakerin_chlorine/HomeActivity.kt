package com.example.prakerin_chlorine

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.prakerin_chlorine.views.HomeFragment
import com.example.prakerin_chlorine.views.PresentFragment
import com.example.prakerin_chlorine.views.SettingFragment
import com.example.prakerin_chlorine.views.TaskFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val fragmentManger = supportFragmentManager
    private val homeFragment = HomeFragment()
    private val presentFragment = PresentFragment()
    private val settingFragment = SettingFragment()
    private val taskFragment = TaskFragment()

    lateinit var bottomNav: BottomNavigationView
    lateinit var session: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        session = getSharedPreferences("session", Context.MODE_PRIVATE)
        var index = intent.getIntExtra("index", 0)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnApplyWindowInsetsListener(null);
        bottomNav.setOnItemSelectedListener {
                item ->
            when(item.itemId){
                R.id.home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.present -> {
                    replaceFragment(presentFragment)
                    true
                }
                R.id.task -> {
                    replaceFragment(taskFragment)
                    true
                }
                R.id.setting -> {
                    replaceFragment(settingFragment)
                    true
                }
                else -> false
            }
        }
        if (index == 0)
        {
            replaceFragment(homeFragment)
        }else if(index == 1)
        {
            replaceFragment(presentFragment)
            bottomNav.menu.findItem(R.id.present).isChecked = true
        }else if(index == 2){
            replaceFragment(taskFragment)
            bottomNav.menu.findItem(R.id.task).isChecked = true
        }else if(index == 3){
            bottomNav.menu.findItem(R.id.setting).isChecked = true
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}