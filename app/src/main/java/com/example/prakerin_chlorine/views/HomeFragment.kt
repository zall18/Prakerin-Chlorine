package com.example.prakerin_chlorine.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.prakerin_chlorine.HomeActivity
import com.example.prakerin_chlorine.R
import com.example.prakerin_chlorine.Response.HomeResponse
import com.example.prakerin_chlorine.Response.Task
import com.example.prakerin_chlorine.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var session : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        session = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE)
        var token = session.getString("token", "")

        var hadir = view.findViewById<TextView>(R.id.total_hadir)
        var alfa = view.findViewById<TextView>(R.id.total_alfa)
        var izin = view.findViewById<TextView>(R.id.total_izin)
        var ts = view.findViewById<TextView>(R.id.total_tugas_selesai)
        var name = view.findViewById<TextView>(R.id.name_home)
        var school = view.findViewById<TextView>(R.id.school_home)

        RetrofitClient.instance.home("Bearer $token").enqueue(object : Callback<HomeResponse>{
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                var data = response.body()
                if (response.isSuccessful)
                {
                    Log.d("Home Response", "onResponse: $data")
                    hadir.text = data?.data?.total_hadir.toString()
                    alfa.text = data?.data?.total_alfa.toString()
                    izin.text = data?.data?.total_izin.toString()
                    ts.text = data?.data?.total_tugas_selesai.toString()
                    name.text = data?.data?.data_user?.full_name
                    school.text = data?.data?.data_user?.nisn
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        var taskButton: AppCompatButton = view.findViewById(R.id.task_button)
        taskButton.setOnClickListener {
            var intent = Intent(requireContext(), HomeActivity::class.java)
            intent.putExtra("index", 2)
            startActivity(intent)
        }
    }

}