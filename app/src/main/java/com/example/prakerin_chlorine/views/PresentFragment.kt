package com.example.prakerin_chlorine.views

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.prakerin_chlorine.EditPofileActivity
import com.example.prakerin_chlorine.HistoryAbsenceActivity

import com.example.prakerin_chlorine.R
import com.example.prakerin_chlorine.Request.PresentRequest
import com.example.prakerin_chlorine.Response.PresentResponse
import com.example.prakerin_chlorine.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresentFragment : Fragment() {

    private lateinit var locationManager: LocationManager
    private lateinit var session: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_present, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val option: ImageView = view.findViewById(R.id.option)
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        session = requireContext().getSharedPreferences("session", Context.MODE_PRIVATE)
        var token = session.getString("token", "")

        option.setOnClickListener {
            popupMenu(option);
        }

        val kehadiran: LinearLayout = view.findViewById(R.id.kehadiran_button)
        kehadiran.setOnClickListener {
            var longitude = 0.0
            var latitude = 0.0


            if (activity?.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request permission
                activity?.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
                activity?.requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        latitude = location.latitude
                        longitude = location.longitude
                        Log.d("locations", "onViewCreated: $latitude $longitude")
                    }
                })

                var presentRequest = PresentRequest(latitude, longitude, "-")

                RetrofitClient.instance.absence("Bearer $token", presentRequest).enqueue(object :
                    Callback<PresentResponse>{
                    override fun onResponse(
                        call: Call<PresentResponse>,
                        response: Response<PresentResponse>
                    ) {
                        var data = response.body()
                        if (response.isSuccessful)
                        {
                            Log.d("Present Request", "onResponse: $data")
                            Toast.makeText(requireContext(), data?.message, Toast.LENGTH_SHORT).show()
                        }else{
                            Log.d("Present Request", "onResponse: $data")
//                            Toast.makeText(requireContext(), data?.message, Toast.LENGTH_SHORT).show()

                        }

                        if (response.code() == 403)
                        {
                            Toast.makeText(requireContext(), "Anda berada diluar lokasi!", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<PresentResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })


            }
        }


    }
    private fun popupMenu(view: View) {
        var popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.option_present, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
                item ->
            when(item.itemId){
                R.id.history -> {
                    startActivity(Intent(requireContext(), HistoryAbsenceActivity::class.java))
                    true
                }
                else -> false
            }
        }

        popupMenu.setOnDismissListener {
            popupMenu.dismiss()
        }

        popupMenu.show()
    }

}