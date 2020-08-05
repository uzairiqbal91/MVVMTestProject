package com.example.mvvmtestproject.data.model

import com.google.gson.annotations.SerializedName

data class CityData(

    @field:SerializedName("serviceCharges")
    val serviceCharges: Int? = null,

    @field:SerializedName("deleted")
    val deleted: Int? = null,

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("modified")
    val modified: String? = null,

    @field:SerializedName("active")
    val active: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("state_id")
    val stateId: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("shortCode")
    val shortCode: String? = null,

    @field:SerializedName("country_id")
    val countryId: Int? = null,

    @field:SerializedName("additionalServiceCharges")
    val additionalServiceCharges: Int? = null
)