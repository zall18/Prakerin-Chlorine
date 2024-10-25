package com.example.prakerin_chlorine

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.example.prakerin_chlorine.Request.LoginRequest
import com.example.prakerin_chlorine.Response.LoginResponse
import com.example.prakerin_chlorine.views.HomeFragment
import com.example.prakerin_chlorine.views.PresentFragment
import com.example.prakerin_chlorine.views.SettingFragment
import com.example.prakerin_chlorine.views.TaskFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var session: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var username: EditText = findViewById(R.id.username_input)
        var password: EditText = findViewById(R.id.password_input)
        session = getSharedPreferences("session", Context.MODE_PRIVATE)
        var editor = session.edit()
        var login: AppCompatButton = findViewById(R.id.login_button)
        login.setOnClickListener {
            var loginRequest = LoginRequest(username.text.toString(), password.text.toString())

            RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("login response", "onResponse: " + response.body())
                    if (response.isSuccessful)
                    {
                        var data = response.body()
                        editor.putString("token", data?.token)
                        editor.commit()
                        startActivity(Intent(applicationContext, HomeActivity::class.java))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })


        }

    }

}