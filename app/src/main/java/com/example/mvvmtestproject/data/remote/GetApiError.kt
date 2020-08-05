package com.example.mvvmtestproject.data.remote

import com.example.mvvmtestproject.data.model.APIError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetApiError @Inject constructor(
    private val retrofit: Retrofit
) {
    fun parseError(response: ResponseBody): APIError? {
        val converter: Converter<ResponseBody?, APIError> = retrofit
            .responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))
        val error: APIError
        error = try {
            converter.convert(response)!!
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }
}