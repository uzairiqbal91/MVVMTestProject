package com.example.mvvmtestproject.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmtestproject.Repository
import com.example.mvvmtestproject.data.model.CityData
import com.example.mvvmtestproject.data.remote.retrofit.Result
import com.example.mvvmtestproject.ui.event.BaseEvent
import com.example.mvvmtestproject.ui.event.CityNavEvent
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.HashMap
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class CityViewModel @ViewModelInject constructor(
    private val gson: Gson,
    private val repository: Repository
) : ViewModel() {

    private val cityNavEvent = MutableLiveData<BaseEvent<CityNavEvent>>()
    val navEvent: LiveData<BaseEvent<CityNavEvent>> = cityNavEvent

    fun getCityData(map: Map<String, String>) {
        viewModelScope.launch {
            cityNavEvent.value = BaseEvent(CityNavEvent.StartLoading)
            try {
                repository.getCity(map).let {
                    when (it) {
                        is Result.Success -> {

                            cityNavEvent.value =
                                BaseEvent(CityNavEvent.StopLoading)

                            val mutableListTutorialType =
                                object :
                                    TypeToken<MutableList<CityData>>() {}.type
                            try {
                                val cityData: MutableList<CityData> =
                                    gson.fromJson(
                                        it.response!!.data.toString(),
                                        mutableListTutorialType
                                    )

                                cityNavEvent.value =
                                    BaseEvent(
                                        CityNavEvent.OnCityData(
                                            cityData
                                        )
                                    )

                            } catch (e: JsonParseException) {
                                e.printStackTrace()
                                cityNavEvent.value =
                                    BaseEvent(CityNavEvent.Error(e.message))
                            }
                        }
                        is Result.Error -> {
                            cityNavEvent.value =
                                BaseEvent(CityNavEvent.StopLoading)
                            cityNavEvent.value =
                                BaseEvent(CityNavEvent.Error(it.error))
                        }
                        is Result.RetrofitError -> {
                            Log.d("StatesHit", "${it.apiError!!.message}")
                            cityNavEvent.value =
                                BaseEvent(CityNavEvent.StopLoading)
                            it.apiError.let {
//                                when (it!!.statusCode) {
//                                    401 -> {
//                                        cityNavEvent.value =
//                                            BaseEvent(CityNavEvent.InvalidUser)
//                                    }
//                                    422 -> {
//                                        BaseEvent(CityNavEvent.Message(it.message))
//                                    }
//                                    else -> {
//                                        cityNavEvent.value =
//                                            BaseEvent(CityNavEvent.Error(it.message))
//                                    }
//                                }
                            }
                        }
                        is Result.Exception -> {
                            cityNavEvent.value =
                                BaseEvent(CityNavEvent.StopLoading)
                            if (it.exception is SocketTimeoutException || it.exception is UnknownHostException || it.exception is ConnectException || it.exception is SSLHandshakeException || it.exception is TimeoutException) {
//                                cityNavEvent.value =
//                                    BaseEvent(CityNavEvent.OnNoInternetAvailable("Login"))
                            } else {
                                cityNavEvent.value =
                                    BaseEvent(CityNavEvent.Error(it.exception.message))
                            }
                        }
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
                cityNavEvent.value = BaseEvent(CityNavEvent.StopLoading)
                cityNavEvent.value =
                    BaseEvent(CityNavEvent.Error(e.message))
            }
        }
    }
}