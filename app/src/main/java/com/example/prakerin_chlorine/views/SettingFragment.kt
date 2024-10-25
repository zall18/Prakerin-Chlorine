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
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.prakerin_chlorine.EditPofileActivity
import com.example.prakerin_chlorine.R
import com.example.prakerin_chlorine.Response.SettingsResponse
import com.example.prakerin_chlorine.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment : Fragment() {
    lateinit var session: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var edit_profile: LinearLayout = view.findViewById(R.id.edit_profile)
        session = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE)
        var token = session.getString("token", "")
        var name: TextView = view.findViewById(R.id.name_pengaturan)
        var nisn: TextView = view.findViewById(R.id.nisn_pengaturan)

        edit_profile.setOnClickListener {
            startActivity(Intent(requireContext(), EditPofileActivity::class.java))
        }

        RetrofitClient.instance.setting("Bearer $token").enqueue(object :
            Callback<SettingsResponse>{
            override fun onResponse(
                call: Call<SettingsResponse>,
                response: Response<SettingsResponse>
            ) {
                var data = response.body()
                Log.d("Setting Response", "onResponse: $data")
                
                if (response.isSuccessful)
                {
                    name.text = data?.data_user?.full_name
                    nisn.text = data?.data_user?.nisn
                }else{
                    Toast.makeText(requireContext(), "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SettingsResponse>, t: Throwable) {
                t.printStackTrace()
            }
            }

        )


    }
}