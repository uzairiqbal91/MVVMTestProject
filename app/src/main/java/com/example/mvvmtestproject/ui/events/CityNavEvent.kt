package com.example.mvvmtestproject.ui.event

import com.example.mvvmtestproject.data.model.CityData

sealed class CityNavEvent {

    object StartLoading : CityNavEvent()
    object StopLoading : CityNavEvent()
    class OnCityData(val list: List<CityData>?) : CityNavEvent()
    class Exception(val exception: java.lang.Exception?) : CityNavEvent()
    class Error(val error: String?) : CityNavEvent()
}