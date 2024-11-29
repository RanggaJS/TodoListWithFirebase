package com.example.todo3.api

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

// Retrofit API Interface
interface ApiService {
    @Multipart
    @POST("upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): UploadResponse
}

// Data class untuk response
data class UploadResponse(
    val imageUrl: String
)

// Retrofit Instance
object RetrofitClient {
    private val client = OkHttpClient.Builder().build()

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.43.224:5000/")
            // Ganti dengan IP server lokal
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
