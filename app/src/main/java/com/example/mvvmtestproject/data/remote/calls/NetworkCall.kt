package com.example.mvvmtestproject.data.remote.calls

import android.util.Log
import com.example.mvvmtestproject.ConnectivityException
import com.example.mvvmtestproject.data.model.BaseResponse
import com.example.mvvmtestproject.data.remote.Connectivity
import com.example.mvvmtestproject.data.remote.GetApiError
import com.example.mvvmtestproject.data.remote.retrofit.WebService
import com.example.mvvmtestproject.data.remote.retrofit.Result
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLHandshakeException

class NetworkCall @Inject constructor(
    val webService: WebService,
    val connectivity: Connectivity,
    val getApiError: GetApiError
) {

    inline fun <reified T : Any> generalRequest(
        request: () -> retrofit2.Response<BaseResponse>
    ): Result =
        try {
            val response = request()
            Log.d("GENERAL_REQ", "Headers---> ${response.headers()}")
            Log.d("GENERAL_REQ", "RawResponse ---> ${response.raw()}")
            if (response.isSuccessful) {
                if (response.body()!!.success!!) {
                    Result.Success(
                        response.body()
                    )
                } else {
                    Result.Error(
                        response.body()!!.message!!
                    )
                }
            } else {
//              to handle response codes other than 200
                Result.RetrofitError(getApiError.parseError(response.errorBody()!!))
            }
        } catch (exception: Exception) {
//              this catch is like onFailure when we used callbacks
            Log.d("GENERAL_REQ", "Exception ${exception.message!!}")
//               this is custom exception to show network error
            Result.Exception(exception)
        }


    suspend inline fun <reified T : Any> get(
        endpoint: String, //end point
        headerToken: String?, //end point
        queryMap: Map<String, String>? //query params
    ): Result {
        Log.d("GENERAL_REQ", "endpoint : $endpoint")
        Log.d("GENERAL_REQ", "headerToken : $headerToken")
        Log.d("GENERAL_REQ", "queryMap: $queryMap")
        //        if no internet connection available throw exception and return
        if (!connectivity.isNetworkConnected()) {
            return Result.Exception(ConnectivityException())
        }
        if (queryMap == null) {
            if (headerToken == null)
                return generalRequest<T> { webService.get(endpoint) }
            else
                return generalRequest<T> { webService.get(endpoint, "Bearer $headerToken") }
        } else {
            if (headerToken == null)
                return generalRequest<T> { webService.get(endpoint, queryMap) }
            else
                return generalRequest<T> {
                    webService.get(
                        endpoint,
                        "Bearer $headerToken",
                        queryMap
                    )
                }
        }
    }


    suspend inline fun <reified T : Any> post(
        endpoint: String, //end point
        headerToken: String?, //end p
        queryMap: Map<String, String>? //query params
    ): Result {
        Log.d("GENERAL_REQ", "endpoint : $endpoint")
        Log.d("GENERAL_REQ", "queryMap: $queryMap")
//        if no internet connection available throw exception and return
        if (!connectivity.isNetworkConnected()) {
            return Result.Exception(ConnectivityException())
        }
        if (queryMap == null)
            if (headerToken == null)
                return generalRequest<T> { webService.post(endpoint) }
            else
                return generalRequest<T> { webService.post(endpoint, "Bearer $headerToken") }
        else
            return generalRequest<T> { webService.post(endpoint, "Bearer $headerToken", queryMap) }
    }
}

