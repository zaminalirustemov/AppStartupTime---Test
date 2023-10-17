package com.lahza.todo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lahza.todo.models.Category
import com.lahza.todo.models.CategoryList
import com.lahza.todo.retrofit.CountryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val api: CountryApi) : ViewModel() {

    private var categoriesLiveData = MutableLiveData<List<Category>>()

    fun getCategories() {
//        startCategoryDownloadWork()

        api.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.isSuccessful) {
                    response.body()?.let { categoryList ->
                        categoriesLiveData.postValue(categoryList.categories)
                    }
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                handleFailure("getCategories", t)
            }
        })
    }

    fun observeCategoriesLiveData(): LiveData<List<Category>> = categoriesLiveData

    private fun handleFailure(callName: String, throwable: Throwable) {
        Log.d("HomeViewModel", "Error in $callName: ${throwable.message}")
    }

//    private fun startCategoryDownloadWork() {
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        val workRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .build()
//
//        WorkManager.getInstance().enqueue(workRequest)
//    }
//
//    fun updateCategories(categories: List<Category>) {
//        categoriesLiveData.postValue(categories)
//    }

}