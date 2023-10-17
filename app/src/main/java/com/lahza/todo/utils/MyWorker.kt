//package com.lahza.todo.utils
//
//import android.content.Context
//import android.util.Log
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.lahza.todo.models.CategoryList
//import com.lahza.todo.retrofit.RetrofitInstance
//import com.lahza.todo.viewModel.HomeViewModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
//
//    override fun doWork(): Result {
//        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
//            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { categoryList ->
//                        val viewModel = HomeViewModel()
//                        viewModel.updateCategories(categoryList.categories)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
//                handleFailure("getCategories", t)
//            }
//        })
//
//        return Result.retry()
//    }
//
//    private fun handleFailure(callName: String, throwable: Throwable) {
//        Log.d("MyWorker", "Error in $callName: ${throwable.message}")
//    }
//}
