package com.example.drugcare_admin.Retrofit

import com.example.drugcare_admin.Models.Admin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header

interface ApiService {
    @POST("/api/admin/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/api/admin/me")
    fun getAdminDetails(@Header("Authorization") token: String): Call<Admin>
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String
)
