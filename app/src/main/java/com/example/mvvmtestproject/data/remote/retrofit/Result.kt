package com.example.mvvmtestproject.data.remote.retrofit

import com.example.mvvmtestproject.data.model.APIError
import com.example.mvvmtestproject.data.model.BaseResponse
import com.google.gson.JsonElement

sealed class Result {
    class Success(val response: BaseResponse?) : Result()
    object UnAuthorized : Result()
    class UnProcessable(val msg: String) : Result()
    class Error(val error: String) : Result()
    class Exception(val exception: java.lang.Exception) : Result()
    class RetrofitError(val apiError: APIError?) : Result()

}