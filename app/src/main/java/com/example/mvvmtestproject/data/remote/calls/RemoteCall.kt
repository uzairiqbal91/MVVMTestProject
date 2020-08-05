package com.example.mvvmtestproject


import com.example.mvvmtestproject.data.remote.calls.NetworkCall
import com.example.mvvmtestproject.data.remote.retrofit.Result
import com.myhelpp.customer.di.PrefManager
import retrofit2.http.FieldMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCall @Inject constructor(
    private val networkCall: NetworkCall,
    private val pref : PrefManager
) {

    suspend fun getCity( map: Map<String, String>): Result =
        networkCall.get<String>(
            "list/city",
            null,
            map
        )
}