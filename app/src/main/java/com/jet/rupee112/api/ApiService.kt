package com.jet.rupee112.api

import com.jet.rupee112.modal.AppVersionRequest
import com.jet.rupee112.modal.Demo
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {

    @POST("checkInstantAppVersionAAPPS")
    suspend fun version(@Body body: AppVersionRequest):Demo


    companion object {
        fun create(): ApiService {
            val logger =
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

            val authInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("Accept","application/json")
//                    .addHeader("Authtoken", Prefs.getLogin()?.app_login_token ?:"")
                    .addHeader("Auth","ZDQ5ZGE4NWNkNGE5ZjVhOTMzYTNmYTgyNjkwYmUwYzg=")
                    .build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(authInterceptor)
                .callTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()


            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vimanotech.com/")
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(ApiService::class.java)
        }
    }
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.vimanotech.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
