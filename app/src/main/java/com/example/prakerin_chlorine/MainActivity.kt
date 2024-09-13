package com.example.prakerin_chlorine

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.example.prakerin_chlorine.views.HomeFragment
import com.example.prakerin_chlorine.views.PresentFragment
import com.example.prakerin_chlorine.views.SettingFragment
import com.example.prakerin_chlorine.views.TaskFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var login: AppCompatButton = findViewById(R.id.login_button)
        login.setOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }

    }

}