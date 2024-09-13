package com.example.prakerin_chlorine

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.example.prakerin_chlorine.views.HomeFragment
import com.example.prakerin_chlorine.views.PresentFragment
import com.example.prakerin_chlorine.views.SettingFragment
import com.example.prakerin_chlorine.views.TaskFragment

class HomeActivity : AppCompatActivity() {
    private val fragmentManger = supportFragmentManager
    private val homeFragment = HomeFragment()
    private val presentFragment = PresentFragment()
    private val settingFragment = SettingFragment()
    private val taskFragment = TaskFragment()

    private lateinit var btnH : ImageButton
    private lateinit var btnP : ImageButton
    private lateinit var btnS : ImageButton
    private lateinit var btnT : ImageButton
    private lateinit var manger : FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        btnH = findViewById(R.id.home)
        btnP = findViewById(R.id.presentButton)
        btnS = findViewById(R.id.settingButton)
        btnT = findViewById(R.id.testButton)
        /**set First Fragment*/
        manger = fragmentManger.beginTransaction()
            .replace(R.id.myView,homeFragment)
        manger.commit()
        btnH.setImageResource(R.drawable.baseline_home_filled_24_white)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun addFragOnClick(view: View) {
        /**set replace fragment*/
        manger = fragmentManger.beginTransaction()
        when(view){
            btnH -> {
                manger.replace(R.id.myView,homeFragment)
                    .commit()
                btnH.setImageResource(R.drawable.baseline_home_filled_24_white)
                btnP.setImageResource(R.drawable.baseline_fingerprint_24)
                btnS.setImageResource(R.drawable.baseline_settings_24)
                btnT.setImageResource(R.drawable.baseline_table_rows_24)

            }
            btnP -> {
                manger.replace(R.id.myView,presentFragment)
                    .commit()
                btnH.setImageResource(R.drawable.baseline_home_filled_24)
                btnP.setImageResource(R.drawable.baseline_fingerprint_24_white)
                btnS.setImageResource(R.drawable.baseline_settings_24)
                btnT.setImageResource(R.drawable.baseline_table_rows_24)
            }
            btnS -> {
                manger.replace(R.id.myView,settingFragment)
                    .commit()
                btnH.setImageResource(R.drawable.baseline_home_filled_24)
                btnP.setImageResource(R.drawable.baseline_fingerprint_24)
                btnS.setImageResource(R.drawable.baseline_settings_24_white)
                btnT.setImageResource(R.drawable.baseline_table_rows_24)
            }
            btnT -> {
                manger.replace(R.id.myView,taskFragment)
                    .commit()
                btnH.setImageResource(R.drawable.baseline_home_filled_24)
                btnP.setImageResource(R.drawable.baseline_fingerprint_24)
                btnS.setImageResource(R.drawable.baseline_settings_24)
                btnT.setImageResource(R.drawable.baseline_table_rows_24_white)
            }
        }
    }
}