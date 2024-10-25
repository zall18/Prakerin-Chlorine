package com.example.prakerin_chlorine.views

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.prakerin_chlorine.Adapter.TaskAdapter
import com.example.prakerin_chlorine.Model.TaskModel
import com.example.prakerin_chlorine.R
import com.example.prakerin_chlorine.Response.Task
import com.example.prakerin_chlorine.Response.TaskResponse
import com.example.prakerin_chlorine.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TaskFragment : Fragment() {

    lateinit var  date_text: TextView
    lateinit var date_picker: TextView
    lateinit var session: SharedPreferences
    lateinit var taskAdapter: TaskAdapter
    lateinit var taskModel : MutableList<Task>

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
        session = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE)
        var token = session.getString("token", "")
        var listView: ListView = view.findViewById(R.id.listview)

        RetrofitClient.instance.task("Bearer $token").enqueue(object : Callback<TaskResponse>{
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                var data = response.body()
                Log.d("Task response", "onResponse: $data")

                if (response.isSuccessful && data != null) {
                    taskModel = mutableListOf<Task>()

                    // Periksa apakah task tidak null atau kosong
                    data.task?.let { taskList ->
                        for (task in taskList) {
                            taskModel.add(task) // Tambahkan setiap task ke dalam taskModel
                        }
                    }

                    // Update adapter dengan data baru
                    taskAdapter = TaskAdapter(requireContext(), taskModel)
                    // Misalnya, recyclerView.adapter = taskAdapter
                    listView.adapter = taskAdapter
            }
        }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                t.printStackTrace()
            }
    })
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