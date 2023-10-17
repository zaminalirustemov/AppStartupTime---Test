package com.lahza.todo.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.lahza.todo.utils.AppContextProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val api: CountryApi by lazy {
        createRetrofit().create(CountryApi::class.java)
    }

    private fun createRetrofit(): Retrofit {
        val chuckerInterceptor = ChuckerInterceptor.Builder(AppContextProvider.getAppContext())
            .alwaysReadResponseBody(true)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(chuckerInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}