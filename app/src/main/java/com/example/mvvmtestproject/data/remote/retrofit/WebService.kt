package com.example.mvvmtestproject.data.remote.retrofit

import com.example.mvvmtestproject.data.model.BaseResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface WebService {

    @GET
    suspend fun get(
        @Url endpoint: String,
        @QueryMap query: @JvmSuppressWildcards Map<String, String>
    ): retrofit2.Response<BaseResponse>

    @GET
    suspend fun get(
        @Url endpoint: String
    ): retrofit2.Response<BaseResponse>

    @GET
    suspend fun get(
        @Url endpoint: String,
        @Header("Authorization") header: String
    ): retrofit2.Response<BaseResponse>

    @GET
    suspend fun get(
        @Url endpoint: String,
        @Header("Authorization") header: String,
        @QueryMap query: @JvmSuppressWildcards Map<String, String>
    ): retrofit2.Response<BaseResponse>

    @FormUrlEncoded
    @POST
    suspend fun post(
        @Url endpoint: String,
        @FieldMap query: @JvmSuppressWildcards Map<String, String>
    ): retrofit2.Response<BaseResponse>

    @FormUrlEncoded
    @POST
    suspend fun post(
        @Url endpoint: String,
        @Header("Authorization") header: String,
        @FieldMap query: @JvmSuppressWildcards Map<String, String>
    ): retrofit2.Response<BaseResponse>

    @FormUrlEncoded
    @POST
    suspend fun post(
        @Url endpoint: String
    ): retrofit2.Response<BaseResponse>

    @FormUrlEncoded
    @POST
    suspend fun post(
        @Url endpoint: String,
        @Header("Authorization") header: String
    ): retrofit2.Response<BaseResponse>
}