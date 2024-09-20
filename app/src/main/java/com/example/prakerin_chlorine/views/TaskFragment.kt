package com.example.prakerin_chlorine.views

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.prakerin_chlorine.R


class TaskFragment : Fragment() {

    lateinit var  date_text: TextView
    lateinit var date_picker: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        date_text = view.findViewById(R.id.date_text)
        date_picker = view.findViewById(R.id.date_picker)
        date_picker.setOnClickListener {
            dateDialog()
        }
    }

    fun dateDialog(){
        val datePickerDialog = DatePickerDialog(
            requireContext(),
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