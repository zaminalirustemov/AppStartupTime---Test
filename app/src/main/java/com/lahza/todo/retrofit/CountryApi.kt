package com.lahza.todo.retrofit

import com.lahza.todo.models.Category
import com.lahza.todo.models.CategoryList
import retrofit2.Call
import retrofit2.http.GET

interface CountryApi {
    @GET("categories.php")
    fun getCategories(): Call<CategoryList>
}