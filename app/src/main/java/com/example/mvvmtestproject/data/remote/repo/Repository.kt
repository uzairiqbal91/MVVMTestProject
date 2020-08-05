package com.example.mvvmtestproject

import com.example.mvvmtestproject.RemoteCall
import com.example.mvvmtestproject.data.remote.retrofit.Result
import retrofit2.http.FieldMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteCall: RemoteCall
) {

    suspend fun getCity(@FieldMap map: Map<String, String>): Result =
        remoteCall.getCity(map)

}