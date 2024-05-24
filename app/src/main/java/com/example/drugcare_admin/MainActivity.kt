package com.example.drugcare_admin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.drugcare_admin.Models.Admin
import com.example.drugcare_admin.Retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var tvAuthCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAuthCode = findViewById(R.id.tvAuthCode)

        val token = intent.getStringExtra("TOKEN")
        if (token != null) {
            fetchAuthCode(token)
        } else {
            tvAuthCode.text = "No token found"
        }
    }

    private fun fetchAuthCode(token: String) {
        RetrofitInstance.api.getAdminDetails("Bearer $token").enqueue(object : Callback<Admin> {
            override fun onResponse(call: Call<Admin>, response: Response<Admin>) {
                if (response.isSuccessful) {
                    val authCode = response.body()?.authCode
                    tvAuthCode.text = "Auth Code: $authCode"
                } else {
                    tvAuthCode.text = "Failed to fetch auth code"
                }
            }

            override fun onFailure(call: Call<Admin>, t: Throwable) {
                tvAuthCode.text = "Error: ${t.message}"
            }
        })
    }
}
