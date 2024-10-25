package com.example.prakerin_chlorine

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoryAbsenceActivity : AppCompatActivity() {

    lateinit var  date_text: TextView
    lateinit var date_picker: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history_absence)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        date_text = findViewById(R.id.date_text)
        date_picker = findViewById(R.id.date_picker)
        date_picker.setOnClickListener {
            dateDialog()
        }

        var back: ImageView = findViewById(R.id.back_history)
        back.setOnClickListener {
            var intent = Intent(applicationContext, HomeActivity::class.java)
            intent.putExtra("index", 1)
            startActivity(intent)
        }


    }

    fun dateDialog(){
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                val monthNames = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
                val selectedDate = "$day/${month + 1}/$year"
                date_text.text = "$day " + monthNames[month - 1]+ " $year"
            },
            2024,
            8,
            9
        )

        datePickerDialog.show()

    }
}